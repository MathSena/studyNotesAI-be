package com.mathsena.studynotesai.config;

import java.util.List;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class CorsConfig {

  @Bean
  public CorsFilter corsFilter() {
    CorsConfiguration corsConfig = new CorsConfiguration();
    corsConfig.setAllowedOrigins(List.of("http://localhost:4200")); // Permitir origem do Angular
    corsConfig.setAllowedMethods(
        List.of("GET", "POST", "PUT", "DELETE", "OPTIONS")); // Métodos permitidos
    corsConfig.setAllowedHeaders(List.of("*")); // Permitir todos os cabeçalhos
    corsConfig.setAllowCredentials(true); // Permitir envio de cookies, autenticação, etc.

    UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
    source.registerCorsConfiguration(
        "/**", corsConfig); // Aplicar essa configuração para todos os endpoints
    return new CorsFilter(source);
  }
}
