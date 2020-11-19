package com.javabom.bomsecurity.member.adapter.config.security;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
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
@EnableGlobalMethodSecurity(securedEnabled = true, prePostEnabled = true)
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
                    .antMatchers("/**").permitAll() // 전체에 접근할수 있게 하고 api 별로 어노테이션을 이용해서 권한을 부여해도 될듯
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling()
                    .accessDeniedPage("/error/access") // 권한이 없는 페이지에 접근할 경우 볼 수 있는 페이지
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
                    .invalidateHttpSession(true);
    }
    // @formatter:on
}
