package com.wildev;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.core.JdbcTemplate;

@SpringBootApplication
public class EcommerceApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(EcommerceApiApplication.class, args);
    }

    @Bean
    @ConditionalOnProperty(
            name = "app.oracle-test.enabled",
            havingValue = "true"
    )
    CommandLineRunner probarOracle(JdbcTemplate jdbcTemplate) {
        return args -> {
            Integer resultado = jdbcTemplate.queryForObject(
                    "SELECT 1 FROM DUAL",
                    Integer.class
            );

            System.out.println("================================");
            System.out.println("CONEXION A ORACLE EXITOSA: " + resultado);
            System.out.println("================================");
        };
    }
}