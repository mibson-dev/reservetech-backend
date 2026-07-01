package com.example.reservetech.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        return http
                .csrf(AbstractHttpConfigurer::disable)
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .sessionManagement(session -> session
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                .authorizeHttpRequests(auth -> auth
                        .requestMatchers("/auth/**").permitAll()
                        .requestMatchers(HttpMethod.GET, "/usuarios/me").authenticated()
                        .requestMatchers("/usuarios/**").hasRole("TI")

                        // Dispositivos e Salas: TI gerencia (cria/edita/remove), todos podem consultar
                        .requestMatchers(HttpMethod.POST, "/dispositivos/**").hasRole("TI")
                        .requestMatchers(HttpMethod.PUT, "/dispositivos/**").hasRole("TI")
                        .requestMatchers(HttpMethod.DELETE, "/dispositivos/**").hasRole("TI")
                        .requestMatchers(HttpMethod.POST, "/salas/**").hasRole("TI")
                        .requestMatchers(HttpMethod.PUT, "/salas/**").hasRole("TI")
                        .requestMatchers(HttpMethod.DELETE, "/salas/**").hasRole("TI")

                        // Reservas: qualquer usuário autenticado pode criar/consultar as próprias
                        .requestMatchers(HttpMethod.GET, "/dispositivos/**", "/salas/**").authenticated()
                        .requestMatchers(HttpMethod.POST, "/reservas").authenticated()
                        .requestMatchers(HttpMethod.GET, "/reservas/**").authenticated()

                        // Aprovar/cancelar reservas de qualquer usuário: só TI
                        .requestMatchers(HttpMethod.PATCH, "/reservas/**").hasRole("TI")

                        .anyRequest().authenticated())
                .addFilterBefore(jwtFilter, UsernamePasswordAuthenticationFilter.class)
                .build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration config = new CorsConfiguration();
        config.setAllowedOrigins(List.of("*")); // libera qualquer origem - só pra desenvolvimento!
        config.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE", "PATCH", "OPTIONS"));
        config.setAllowedHeaders(List.of("*"));

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", config);
        return source;
    }
}