package ru.otus.junit.dao;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.junit.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс PersonDaoImpl")
class PersonDaoImplTest {

    private PersonDaoImpl personDao() {
        return new PersonDaoImpl(persons());
    }

    private List<Person> persons() {
        List<Person> personList = new ArrayList<>();
        personList.add(testPerson());
        personList.add(new Person(33, "Alex"));
        personList.add(new Person(22, "Vladimir"));
        return personList;
    }

    private Person testPerson() {
        return new Person(42, "Ivan");
    }

    // убрано из задания, оставляю для себя
    @DisplayName("существует поле - список Person-ов")
    @Test
    void shouldHavePersonList() {
        assertDoesNotThrow(() -> personDao().getClass().getDeclaredField("personList"));
    }

    @DisplayName("находит Person")
    @Test
    void shouldFindName() {
        Person person = testPerson();
        PersonDaoImpl personDao = personDao();
        personDao.save(person);
        assertEquals(person, personDao.getByName(person.getName()));
    }

    @DisplayName("бросает PersonNotFoundException если не найден")
    @Test
    void shouldThrowPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> personDao().getByName("Incorrect name"));
    }

    @DisplayName("находит 3 Person")
    @Test
    void shouldFind3Persons() {
        assertEquals(3, personDao().getAll().size());
    }

    @DisplayName("удаляет Person")
    @Test
    void shouldDeleteName() {
        PersonDaoImpl personDao = personDao();
        personDao.deleteByName("Ivan");
        assertThrows(PersonNotFoundException.class, () -> personDao.getByName("Ivan"));
    }

    @DisplayName("бросает PersonNotFoundException")
    @Test
    void shouldThrowPersonNotFoundException2() {
        assertThrows(PersonNotFoundException.class, () -> personDao().deleteByName("Incorrect name"));
    }

    @DisplayName("должен добавить Person")
    @Test
    void shouldAddPerson() {
        PersonDaoImpl personDao = personDao();
        assertEquals(3, personDao.getAll().size());
        personDao.save(new Person(111, "NEW PERSON"));
        assertEquals(4, personDao.getAll().size());
    }

    @DisplayName("должен перезаписать Person")
    @Test
    void shouldOverridePerson() {
        Person person = testPerson();
        PersonDaoImpl personDao = personDao();
        personDao.save(new Person(person.getAge() + 3, person.getName()));
        assertAll(
                () -> assertEquals(3, personDao.getAll().size()),
//                () -> assertTrue(personDao.getAll().stream().map(Person::getName)
//                        .collect(Collectors.toList()).contains("Ivan")),
                () -> assertThat(personDao.getAll()).extracting(Person::getName).contains("Ivan")
        );
    }

}