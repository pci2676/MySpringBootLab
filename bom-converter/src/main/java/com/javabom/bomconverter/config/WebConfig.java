package com.javabom.bomconverter.config;

import com.javabom.bomconverter.converter.StringToLocalDateTimeConverter;
import com.javabom.bomconverter.converter.StringToLocalDateTimeConverter2;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addFormatters(FormatterRegistry registry) {
        //나중에 등록한 컨버터가 동작하게 된다.
        registry.addConverter(new StringToLocalDateTimeConverter());
        registry.addConverter(new StringToLocalDateTimeConverter2());
    }
}
