package com.javabom.bomspringboot.client;

import com.javabom.bomspringboot.client.config.DefaultUuidClientBuilder;
import org.springframework.web.client.RestTemplate;

public class DefaultUuidClient implements UuidClient {

    private final RestTemplate restTemplate;

    public DefaultUuidClient(final DefaultUuidClientBuilder defaultUuidClientBuilder) {
        this.restTemplate = defaultUuidClientBuilder.get()
                .build();
    }

    @Override
    public String getUuid() {
        return this.restTemplate.getForObject("/uuid", String.class);
    }
}
