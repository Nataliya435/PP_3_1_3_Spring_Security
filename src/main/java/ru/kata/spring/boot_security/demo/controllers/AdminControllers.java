package ru.kata.spring.boot_security.demo.controllers;


import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.models.Role;
import ru.kata.spring.boot_security.demo.services.PersonService;
import ru.kata.spring.boot_security.demo.services.RoleService;

import java.util.List;

@Controller
@RequestMapping(value = "/admin", method = RequestMethod.GET)
public class AdminControllers {

    private final PersonService personService;
    private final RoleService roleService;

    public AdminControllers(PersonService personService, RoleService roleService) {
        this.personService = personService;
        this.roleService = roleService;
    }

    @GetMapping("/all")
    public String showAllPerson(Model model) {
        List<Person> allPersons = personService.getAllPersons();
        model.addAttribute("allPersons", allPersons);
        return "admin/all-Persons";
    }


    @GetMapping("/new")
    public String newUser(Model model, @ModelAttribute("person") Person person) {
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesAdd", roles);
        return "admin/add-Person";
    }

    @PostMapping()
    public String createUser(@ModelAttribute("person") Person person) {
        personService.savePerson(person);
        return "redirect:/admin/all";
    }


    @GetMapping("/{id}/edit")
    public String edit(Model model, @PathVariable("id") Long id) {
        model.addAttribute("person", personService.getPersonById(id));
        List<Role> roles = roleService.getUniqAllRoles();
        model.addAttribute("rolesUpdate", roles);
        return "admin/edit-User";
    }

    @PatchMapping("/{id}")
    public String update(@ModelAttribute("person") Person person) {
        personService.updatePerson(person);
        return "redirect:/admin/all";
    }


    @DeleteMapping("/{id}")
    public String delete(@PathVariable("id") Long id) {
        personService.deletePerson(id);
        return "redirect:/admin/all";
    }
}

