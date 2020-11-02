package com.javabom.bomspringboot.client.config;

import org.springframework.boot.web.client.RestTemplateBuilder;

public class DefaultUuidClientBuilder {
    public RestTemplateBuilder get() {
        return new RestTemplateBuilder()
                .rootUri("https://httpbin.org");
    }
}
