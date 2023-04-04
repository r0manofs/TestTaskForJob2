package ru.romanov.StoreTZ.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.romanov.StoreTZ.security.UserEntityDetails;

@Controller
@RequestMapping
public class FirstRestController {

    @GetMapping("/hello")
    public String SayHello() {
        return "hello";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUser());

        return "hello";
    }

    @GetMapping("/admin")
    public String adminPage(){
        return "admin";
    }
}
