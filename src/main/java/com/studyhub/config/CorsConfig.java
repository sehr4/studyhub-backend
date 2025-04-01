package com.studyhub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Configuration class for enabling CORS (Cross-Origin Resource Sharing).
 * Allows the frontend to communicate with the backend from different origins.
 */
@Configuration
public class CorsConfig implements WebMvcConfigurer {

    // Logger to print messages to the console for understanding configuration
    private static final Logger logger = LoggerFactory.getLogger(CorsConfig.class);

    /**
     * Configures CORS mappings to allow requests from the frontend.
     *
     * @param registry the CorsRegistry to configure
     */
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        logger.info("Configuring CORS mappings");
        registry.addMapping("/**")
                .allowedOrigins("http://localhost:5173", "https://thankful-glacier-09f37ef03.6.azurestaticapps.net") // Vite default and Azure URL
                .allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")
                .allowedHeaders("*")
                .allowCredentials(true);
    }
}