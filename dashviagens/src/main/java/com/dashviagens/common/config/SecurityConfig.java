package com.dashviagens.common.config;

import com.dashviagens.admin.security.JwtAuthFilter;
import com.dashviagens.admin.security.JwtService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final JwtService jwtService;

    public SecurityConfig(JwtService jwtService) {
        this.jwtService = jwtService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("http://localhost:5173", "http://localhost:3000", "http://localhost:8080"));
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        config.setAllowedHeaders(List.of("*"));
        config.setAllowCredentials(true);
        config.setMaxAge(3600L);
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .sessionManagement(sm -> sm.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth

                        // --- Infraestrutura e documentação ---
                        .requestMatchers(HttpMethod.OPTIONS, "/**").permitAll()
                        .requestMatchers("/docs/**", "/swagger-ui/**", "/swagger-ui.html",
                                "/v3/api-docs/**", "/webjars/**").permitAll()

                        // --- Auth ---
                        .requestMatchers(HttpMethod.POST, "/api/admin/auth/login").permitAll()

                        // --- Leitura pública: endpoints explícitos ---
                        .requestMatchers(HttpMethod.GET, "/api/exchange/rates").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/exchange/convert").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/countries").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/countries/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/attractions").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/attractions/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/costs").permitAll()
                        .requestMatchers(HttpMethod.GET, "/api/costs/**").permitAll()

                        // --- Calculadora: POST público ---
                        .requestMatchers(HttpMethod.POST, "/api/costs/estimate").permitAll()

                        // --- Escrita restrita a ADMIN ---
                        .requestMatchers(HttpMethod.POST,
                                "/api/countries/**", "/api/attractions/**", "/api/costs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.PUT,
                                "/api/countries/**", "/api/attractions/**", "/api/costs/**").hasRole("ADMIN")
                        .requestMatchers(HttpMethod.DELETE,
                                "/api/countries/**", "/api/attractions/**", "/api/costs/**").hasRole("ADMIN")

                        .anyRequest().authenticated())
                .addFilterBefore(new JwtAuthFilter(jwtService),
                        UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}