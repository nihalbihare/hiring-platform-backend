package com.hiring_platform.Hiring.Platform.api;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfigurationSource;

@Configuration
public class SecurityConfig {

    private final CorsConfigurationSource corsConfigurationSource;

    public SecurityConfig(CorsConfigurationSource corsConfigurationSource) {
        this.corsConfigurationSource = corsConfigurationSource;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(cors -> cors.configurationSource(corsConfigurationSource)) // ✅ Apply your CorsConfig
            .csrf(csrf -> csrf.disable()) // Not needed for API calls
            .authorizeHttpRequests(auth -> auth
                .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll() // ✅ Allow preflight
                .anyRequest().authenticated()
            );

        return http.build();
    }
}
