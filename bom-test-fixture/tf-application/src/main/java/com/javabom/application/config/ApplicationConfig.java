package com.javabom.application.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan(value = {
        "com.javabom.application.domain.*"
})
@EntityScan(value = {
        "com.javabom.core.domain.*"
})
@EnableJpaRepositories(value = {
        "com.javabom.application.domain.*"
})
public class ApplicationConfig {
}
