package ru.romanov.StoreTZ.controllers;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import ru.romanov.StoreTZ.security.UserEntityDetails;

@Controller
@RequestMapping
public class FirstRestController {
    @ResponseBody
    @GetMapping("/hello")
    public String SayHello() {
        //TODO не редиректит на страницу с logout
        return "hi mAn!";
    }

    @GetMapping("/showUserInfo")
    public String showUserInfo() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        UserEntityDetails userDetails = (UserEntityDetails) authentication.getPrincipal();

        System.out.println(userDetails.getUser());

        return "hello";
    }
}
