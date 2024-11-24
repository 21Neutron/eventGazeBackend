package com.group9.eventgaze.config;

import com.group9.eventgaze.service.CustomUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig {

    private final CustomUserDetailsService userDetailsService;

    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }

    @Autowired
    private JwtAuthenticationFilter jwtAuthenticationFilter;

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http.csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth

                        // Publicly accessible endpoints

                        .requestMatchers("/eventgaze/auth/**").permitAll()
                        .requestMatchers(
                                                    "/eventgaze/events/eventId/id/**","/eventgaze/events/getAll",
                                                    "/eventgaze/events/category/id/**").permitAll()


                        .requestMatchers("/eventgaze/collegeList/getAll",
                                                   "/eventgaze/collegeList/id/**").permitAll()
                        .requestMatchers("/eventgaze/category/getAll","/error").permitAll()

                        // Role-based secured endpoints

                        .requestMatchers(HttpMethod.POST, "/eventgaze/events/create").hasAnyRole("PUBLISHER","STUDENT")
                        .requestMatchers(HttpMethod.PUT, "/eventgaze/events/eventEdit/id/**").hasRole("PUBLISHER")
                        .requestMatchers(HttpMethod.DELETE, "/eventgaze/events/id/**").hasRole("PUBLISHER")
                        .requestMatchers("/eventgaze/events/**").hasAnyRole("STUDENT", "PUBLISHER")
                        .anyRequest().authenticated()
                )
                .addFilterBefore(jwtAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }


    // Define an AuthenticationManager Bean
    @Bean
    public AuthenticationManager authManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());

        return authenticationManagerBuilder.build();
    }

}
