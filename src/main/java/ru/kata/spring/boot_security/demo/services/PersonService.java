package ru.kata.spring.boot_security.demo.services;


import ru.kata.spring.boot_security.demo.models.Person;

import java.util.List;

public interface PersonService {
    public List<Person> getAllPersons();

    public Person getPersonById(Long id);

    public Person getPersonByLogin(String login);

    public void savePerson(Person person);

    public void updatePerson(Person updatePerson);

   public void deletePerson(Long id);
}



