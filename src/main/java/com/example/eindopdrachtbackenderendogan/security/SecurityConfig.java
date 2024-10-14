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
                            .requestMatchers(HttpMethod.DELETE, "/users/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT,"/users/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/auth").permitAll()
                            .requestMatchers(HttpMethod.POST, "/menus").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/menus").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/menus/*").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.DELETE, "/menus/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST,"/menus/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/drinks").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/drinks").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET, "/drinks/*").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.PUT, "/drinks/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/drinks/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/reservations").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.GET, "/reservations/*").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.PUT, "/reservations/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.DELETE, "/reservations/**").hasRole("ADMIN")
                            .requestMatchers(HttpMethod.POST, "/reservations").hasAnyRole("ADMIN", "USER")
                            .requestMatchers(HttpMethod.GET,"/roles").hasRole("ADMIN")
                            .requestMatchers("/profiles", "/profiles/*", "/profiles/**" ).authenticated()
                            .requestMatchers(HttpMethod.GET,"/roles").hasRole("ADMIN")
                            .anyRequest().denyAll()
                    )
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(csrf -> csrf.disable())
                    .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

            return http.build();
        }

        }



