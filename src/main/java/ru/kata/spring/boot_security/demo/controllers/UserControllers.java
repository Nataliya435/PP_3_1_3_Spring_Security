package ru.kata.spring.boot_security.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.services.PersonServiceImpl;

import java.security.Principal;


@Controller

public class UserControllers {

    private final PersonServiceImpl personService;

    public UserControllers(PersonServiceImpl personService) {
        this.personService = personService;
    }

    @GetMapping("/user")
    public String person(Model model, Principal principal) {
        Person person = personService.getPersonByLogin(principal.getName());
        model.addAttribute("person", personService.getPersonById(person.getId()));


        return "person/person";
    }

}

