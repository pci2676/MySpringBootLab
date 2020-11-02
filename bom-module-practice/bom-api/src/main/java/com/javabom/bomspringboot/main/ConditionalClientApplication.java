package com.javabom.bomspringboot.main;

import com.javabom.bomspringboot.config.ApiConfiguration;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@Import(ApiConfiguration.class)
@SpringBootApplication
public class ConditionalClientApplication {
    public static void main(String[] args) {
        SpringApplication.run(ConditionalClientApplication.class, args);
    }
}
