package com.aditya.SpringSecurity.Controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpRequest;

@RestController
public class HelloController {
    @GetMapping("/")
    public String greet(HttpServletRequest request)
    {
        return "Welcome to aditya"+request.getSession().getId();
    }
}
