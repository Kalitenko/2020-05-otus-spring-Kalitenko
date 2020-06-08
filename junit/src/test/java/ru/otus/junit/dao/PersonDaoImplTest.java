package ru.otus.junit.dao;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.junit.domain.Person;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс PersonDaoImpl")
class PersonDaoImplTest {

    private PersonDaoImpl personDao = new PersonDaoImpl();

    private List<Person> personList = new ArrayList<>();
    private Person testPerson = new Person(42, "Ivan");
    private Person overridePerson = new Person(22, "Ivan");

    @BeforeEach
    void init() {
        personList.add(testPerson);
        personList.add(new Person(33, "Alex"));
        personList.add(new Person(22, "Vladimir"));
        personDao.setPersonList(personList);
    }

    // убрано из задания, оставляю для себя
    @DisplayName("существует поле - список Person-ов")
    @Test
    void shouldHavePersonList() {
        assertDoesNotThrow(() -> personDao.getClass().getDeclaredField("personList"));
    }

    @DisplayName("находит Person")
    @Test
    void shouldFindName() {
        assertEquals(testPerson, personDao.getByName("Ivan"));
    }

    @DisplayName("бросает PersonNotFoundException")
    @Test
    void shouldThrowPersonNotFoundException() {
        assertThrows(PersonNotFoundException.class, () -> personDao.getByName("Peter"));
    }

    @DisplayName("находит 3 Person")
    @Test
    void shouldFind3Persons() {
        assertEquals(3, personDao.getAll().size());
    }

    @DisplayName("удаляет Person")
    @Test
    void shouldDeleteName() {
        personDao.deleteByName("Ivan");
        assertThrows(PersonNotFoundException.class, () -> personDao.getByName("Ivan"));
    }

    @DisplayName("бросает PersonNotFoundException")
    @Test
    void shouldThrowPersonNotFoundException2() {
        assertThrows(PersonNotFoundException.class, () -> personDao.deleteByName("Peter"));
    }

    @DisplayName("должен добавить Person")
    @Test
    void shouldAddPerson() {
        Person alex = personList.remove(1);
        personDao.setPersonList(personList);
        personDao.save(alex);
        assertEquals(3, personDao.getAll().size());
    }

    @DisplayName("должен перезаписать Person")
    @Test
    void shouldOverridePerson() {
        personDao.save(overridePerson);
        assertAll(
                () -> assertEquals(3, personDao.getAll().size()),
//                () -> assertTrue(personDao.getAll().stream().map(Person::getName)
//                        .collect(Collectors.toList()).contains("Ivan")),
                () -> assertThat(personDao.getAll()).extracting(Person::getName).contains("Ivan")
        );
    }

}