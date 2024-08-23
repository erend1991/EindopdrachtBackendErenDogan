package com.example.eindopdrachtbackenderendogan.security;

import com.example.eindopdrachtbackenderendogan.repositories.UserRepository;
import com.example.eindopdrachtbackenderendogan.security.JwtRequestFilter;
import com.example.eindopdrachtbackenderendogan.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
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
                            .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                            .requestMatchers(HttpMethod.POST, "/menu").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/menu/*").permitAll()
                            .requestMatchers(HttpMethod.DELETE, "/menu/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/menu/*/drink/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/drink").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/drink").permitAll()
                            .requestMatchers(HttpMethod.PUT, "/drink").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/drink").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/reservation").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/reservation").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/reservation").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/reservation").hasAnyRole("ADMIN", "USER")
                            .requestMatchers("/profiles", "/profiles/*").authenticated()
                            .anyRequest().denyAll()
                    )
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(csrf -> csrf.disable())
                    .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        }



