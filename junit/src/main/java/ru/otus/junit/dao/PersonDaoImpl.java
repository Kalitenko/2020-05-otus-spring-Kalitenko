package ru.otus.junit.dao;

import ru.otus.junit.domain.Person;

import java.util.List;
import java.util.Optional;

public class PersonDaoImpl implements PersonDao {

    private List<Person> personList;

    @Override
    public Person getByName(String name) throws PersonNotFoundException {
        Optional<Person> optionalPerson = personList.stream().filter(x -> x.getName().equals(name)).findAny();
        if (optionalPerson.isPresent())
            return optionalPerson.get();
        else
            throw new PersonNotFoundException(name + " not found");
    }

    @Override
    public List<Person> getAll() {
        return personList;
    }

    @Override
    public void deleteByName(String name) throws PersonNotFoundException {
        Person person = getByName(name);
        personList.remove(person);
    }

    @Override
    public void save(Person person) {

        boolean contains = false;

        for (int i = 0; i < personList.size(); i++) {
            if (personList.get(i).getName().equals(person.getName())) {
                contains = true;
                personList.set(i, person);
                break;
            }
        }
        if (!contains)
            personList.add(person);

    }

    public void setPersonList(List<Person> personList) {
        this.personList = personList;
    }
}
