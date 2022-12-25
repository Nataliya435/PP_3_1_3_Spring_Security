package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.kata.spring.boot_security.demo.models.Person;

import javax.validation.Valid;


@Controller
@RequestMapping("/auth")
public class AuthControllers {
    @GetMapping("/login")
    public String login(){
        return "auth/login";
    }

}
