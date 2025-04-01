package com.studyhub.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Configuration class for security-related beans.
 * Provides a BCryptPasswordEncoder for password hashing.
 */
@Configuration
public class SecurityConfig {

    // Logger to print messages to the console for understanding configuration
    private static final Logger logger = LoggerFactory.getLogger(SecurityConfig.class);

    /**
     * Creates a BCryptPasswordEncoder bean for password hashing.
     *
     * @return a BCryptPasswordEncoder instance
     */
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        logger.info("Creating BCryptPasswordEncoder bean");
        return new BCryptPasswordEncoder();
    }
}