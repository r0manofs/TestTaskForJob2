package ru.romanov.StoreTZ.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ru.romanov.StoreTZ.security.UserEntityDetails;

@RestController
@RequestMapping("/hello")
public class FirstRestController {
    @ResponseBody
    @GetMapping("")
    public String SayHello() {
        return "hi man";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUser());

        return "hello";
    }
}
