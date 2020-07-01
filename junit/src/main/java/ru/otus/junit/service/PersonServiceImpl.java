package ru.otus.junit.service;

import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.dao.PersonNotFoundException;
import ru.otus.junit.domain.Person;

import java.util.List;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    @Override
    public Person getByName(String name) {
        try {
            return dao.getByName(name);
        } catch (PersonNotFoundException e) {
            return null;
        }
    }

    @Override
    public List<Person> getAll() {
        return dao.getAll();
    }

    @Override
    public boolean existsWithName(String name) {
        return null != getByName(name);
    }

    @Override
    public void save(Person p) {
        dao.save(p);
    }
}
