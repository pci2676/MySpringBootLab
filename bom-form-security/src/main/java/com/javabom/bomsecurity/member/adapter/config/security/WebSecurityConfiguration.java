package com.javabom.bomsecurity.member.adapter.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.csrf.CookieCsrfTokenRepository;

@RequiredArgsConstructor
@Configuration
@EnableWebSecurity
public class WebSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final LoginLogSuccessHandler loginLogSuccessHandler;
    private final LoginLogFailureHandler loginLogFailureHandler;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public void configure(WebSecurity web) {
        web
                .ignoring()
                .antMatchers("/js/**", "/css/**", "/images/**")
                .antMatchers("/h2-console/**");
    }

    // @formatter:off
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                    .antMatchers("/", "/home").permitAll()
                    .antMatchers("/sign-up", "/api/members").permitAll()
                    .anyRequest().authenticated()
                .and()
                .csrf()
                    .csrfTokenRepository(CookieCsrfTokenRepository.withHttpOnlyFalse()) // csrf를 위한 xsrf-token 생성
                .and()
                .formLogin()
                    .loginPage("/login")
                    .loginProcessingUrl("/api/members/login")
                    .defaultSuccessUrl("/")
                    .successHandler(loginLogSuccessHandler)
                    .failureUrl("/login")
                    .failureHandler(loginLogFailureHandler)
                    .permitAll()
                .and()
                .logout()
                    .logoutUrl("/api/members/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .permitAll();
    }
    // @formatter:on
}
