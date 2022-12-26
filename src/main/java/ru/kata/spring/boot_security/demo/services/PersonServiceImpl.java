package ru.kata.spring.boot_security.demo.services;


import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.kata.spring.boot_security.demo.models.Person;
import ru.kata.spring.boot_security.demo.repositories.PersonRepository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Service
public class PersonServiceImpl implements PersonService, UserDetailsService {
    @PersistenceContext
    private EntityManager manager;
    private final PersonRepository personRepository;

    public PersonServiceImpl(PersonRepository personRepository) {
        this.personRepository = personRepository;
    }


    @Override
    @Transactional(readOnly = true)
    public List<Person> getAllPersons() {
        List<Person> users = manager.createQuery("select us from Person us").getResultList();
        return users;
    }

    @Override
    @Transactional
    public Person getPersonById(Long id) {
        return manager.find(Person.class,id);
    }

    @Override
    @Transactional
    public Person getPersonByLogin(String login) {
        return manager.find(Person.class,login);
    }


    @Override
    @Transactional
    public void savePerson(Person person) {
        manager.persist(person);
    }


    @Override
    @Transactional
    public void updatePerson(Person updatePerson) {
        manager.persist(updatePerson);
    }

    @Override
    @Transactional
    public void deletePerson(Long id) {
        manager.remove(getPersonById(id));
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        Person person = personRepository.findByLogin(s);
        if (person==null)
            throw new UsernameNotFoundException("Такого пользователя нет");
        return person;

    }


}
