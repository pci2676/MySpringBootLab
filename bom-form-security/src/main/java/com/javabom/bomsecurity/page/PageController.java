package com.javabom.bomsecurity.page;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class PageController {

    @GetMapping({"/", "/home"})
    public String homePage() {
        return "/home";
    }

    @GetMapping("/login")
    public String loginPage() {
        return "/login";
    }

    @GetMapping("/sign-up")
    public String signUpPage() {
        return "/sign-up";
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/admin")
    public String adminPage() {
        return "/admin";
    }

    @GetMapping("/error/access")
    public String notAllowedPage() {
        return "/error/access-denied";
    }
}
