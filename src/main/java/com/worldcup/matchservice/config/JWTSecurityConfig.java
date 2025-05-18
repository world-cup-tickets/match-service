package com.worldcup.matchservice.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class JWTSecurityConfig {

//    @Bean
//    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//        http
//                .csrf(AbstractHttpConfigurer::disable)
//                .authorizeHttpRequests(auth -> auth
//                        .requestMatchers("/actuator/**").permitAll()
//                        .requestMatchers(HttpMethod.GET, "/api/v1/match/getAllMatches").hasAuthority("SCOPE_match.read")
//                        .requestMatchers(HttpMethod.GET, "/api/v1/match/{id}").hasAuthority("SCOPE_match.read")
//                        .requestMatchers(HttpMethod.POST, "/api/v1/match/create").hasAuthority("SCOPE_match.write")
//                        .requestMatchers(HttpMethod.PUT, "/api/v1/match/{id}/update").hasAuthority("SCOPE_match.write")
//                        .anyRequest().authenticated()
//                )
//                .oauth2ResourceServer(oauth2 -> oauth2.jwt(Customizer.withDefaults()));
//        return http.build();
//    }
}