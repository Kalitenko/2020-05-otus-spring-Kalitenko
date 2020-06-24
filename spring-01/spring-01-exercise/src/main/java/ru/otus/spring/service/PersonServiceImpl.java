package ru.otus.spring.service;

import ru.otus.spring.dao.PersonDao;
import ru.otus.spring.domain.Person;

public class PersonServiceImpl implements PersonService {

    private final PersonDao dao;

    private PersonDao propertyDao;

    public PersonServiceImpl(PersonDao dao) {
        this.dao = dao;
    }

    public Person getByName(String name) {
        return dao.findByName(name);
    }

    public void setPropertyDao(PersonDao propertyDao) {
        this.propertyDao = propertyDao;
    }
}
