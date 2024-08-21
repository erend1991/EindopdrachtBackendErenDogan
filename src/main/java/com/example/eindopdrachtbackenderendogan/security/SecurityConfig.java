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
                            .requestMatchers(HttpMethod.POST, "/drink").hasRole("ADMIN")
                            .requestMatchers("/secret").hasRole("ADMIN")
                            .requestMatchers("/hello").authenticated()
                            .requestMatchers("/profiles", "/profiles/*").authenticated()
                            .anyRequest().denyAll()
                    )
                    .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                    .csrf(csrf -> csrf.disable())
                    .addFilterBefore(new JwtRequestFilter(jwtService, userDetailsService()), UsernamePasswordAuthenticationFilter.class);

            return http.build();
//
//            @Bean
//            protected SecurityFilterChain filter (HttpSecurity http) throws Exception {
//
//                http
//                        .csrf(csrf -> csrf.disable())
//                        .httpBasic(basic -> basic.disable())
//                        .cors(Customizer.withDefaults())
//                        .authorizeHttpRequests(auth ->
//                                        auth
                                                // Wanneer je deze uncomments, staat je hele security open. Je hebt dan alleen nog een jwt nodig.
//                .requestMatchers("/**").permitAll()
//                                                .requestMatchers(HttpMethod.POST, "/users").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.GET,"/users").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.POST,"/users/**").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.DELETE, "/users/**").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.POST, "/drink").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.DELETE, "/drink/**").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.POST, "/menu").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.DELETE, "/menu/**").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.POST, "/reservation").hasRole("ADMIN")
//                                                .requestMatchers(HttpMethod.DELETE, "/reservation/**").hasRole("ADMIN")
                                                // Je mag meerdere paths tegelijk definieren
//                                                .requestMatchers("/cimodules", "/remotecontrollers", "/televisions", "/wallbrackets").hasAnyRole("ADMIN", "USER")
//                                                .requestMatchers("/authenticated").authenticated()
//                                                .requestMatchers("/authenticate").permitAll()
//                                                .anyRequest().denyAll()
//                        )
//                        .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//                http.addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);
//                return http.build();
//            }
        }
    }


