package ru.kata.spring.boot_security.demo.services;


import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService {

    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }

    @Override
    @Transactional
    public Person getPersonById(Long id) {
        return personRepository.findById(id).get();
    }

    @Override
    @Transactional
    public Person getPersonByLogin(String login) {
        return personRepository.findByLogin(login);
    }


    @Override
    @Transactional
    public void savePerson(Person person) {
        personRepository.save(person);
    }


    @Override
    @Transactional
    public void updatePerson(Person updatePerson) {
        personRepository.save(updatePerson);
    }


    @Override
    @Transactional
    public void deletePerson(Long id) {personRepository.deleteById(id);
    }


}
