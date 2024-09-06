package com.example.eindopdrachtbackenderendogan.security;

import com.example.eindopdrachtbackenderendogan.repositories.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

    @Configuration
    @EnableWebSecurity
    public class SecurityConfig  {

        private final JwtService jwtService;
        private final UserRepository userRepository;

        public SecurityConfig(JwtService service, UserRepository userRepos) {
            this.jwtService = service;
            this.userRepository = userRepos;
        }

        @Bean
        public AuthenticationManager authenticationManager(UserDetailsService udService, PasswordEncoder passwordEncoder) {
            var auth = new DaoAuthenticationProvider();
            auth.setPasswordEncoder(passwordEncoder);
            auth.setUserDetailsService(udService);
            return new ProviderManager(auth);
        }

        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        @Bean
        public UserDetailsService userDetailsService() {
            return new MyUserDetailsService(this.userRepository);
        }

        @Bean
        public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
            http
                    .authorizeHttpRequests(auth -> auth
                            .requestMatchers(HttpMethod.POST, "/users").permitAll()
                            .requestMatchers(HttpMethod.GET, "/users").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                            .requestMatchers(HttpMethod.POST, "/menu").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/menu").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/menu/*").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.DELETE, "/menu/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/menu/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/drink").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/drink").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.PUT, "/drink/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/drink/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/reservations").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/reservations/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/reservations").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.PUT,"/users/{username}/profile/{profileId}").hasRole("ADMIN")
                            .requestMatchers("/profiles", "/profiles/*").authenticated()
                            .anyRequest().denyAll()
                    )
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(csrf -> csrf.disable())
                    .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        }



