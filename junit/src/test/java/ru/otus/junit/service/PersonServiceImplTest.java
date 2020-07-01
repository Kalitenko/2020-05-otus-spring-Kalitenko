package ru.otus.junit.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.junit.dao.PersonDao;
import ru.otus.junit.dao.PersonNotFoundException;
import ru.otus.junit.domain.Person;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class PersonServiceImplTest {

    @Mock
    private PersonDao personDao;

    private PersonService personService;

    @BeforeEach
    void setUp() {
        personService = new PersonServiceImpl(personDao);
    }

    @Test
    void getByName() {
        given(personDao.getByName(any()))
                .willReturn(new Person(10, "Ivan"));

        assertThat(personService.getByName("Ivan"))
                .isNotNull();
    }

    @Test
    void getByName2() {

        given(personDao.getByName(eq("Ivan")))
                .willReturn(new Person(10, "Ivan"));

        assertThat(personService.getByName("Ivan"))
                .isEqualToComparingFieldByField(new Person(10, "Ivan"));

        verify(personDao).getByName(eq("Ivan"));
    }

    @Test
    void getByName3() {

        given(personDao.getByName(anyString()))
                .willReturn(new Person(10, "Ivan"));
        assertThat(personService.getByName("Ivan"))
                .usingRecursiveComparison().isEqualTo(new Person(10, "Ivan"));
    }

    @Test
    void getAll() {
        given(personDao.getAll()).willReturn(Arrays.asList(
                new Person(22, "Ivan"),
                new Person(33, "Alex"),
                new Person(44, "Vladimir")
        ));
        assertThat(personService.getAll().size()).isEqualTo(3);
        verify(personDao).getAll();
    }

    @Test
    void existsWithName() {
        given(personDao.getByName(eq("Ivan")))
                .willReturn(new Person(10, "Ivan"));
        given(personDao.getByName(eq("Alex")))
                .willThrow(new PersonNotFoundException("Person with name \"Alex\" not found"));

        assertAll(
                () -> assertThat(personService.existsWithName("Ivan")).isTrue(),
                () -> assertThat(personService.existsWithName("Alex")).isFalse()
        );

        verify(personDao).getByName(eq("Ivan"));
        verify(personDao).getByName(eq("Alex"));

    }

    @Test
    void save() {
        Person ivan = new Person(10, "Ivan");
        personDao.save(ivan);
        verify(personDao).save(ivan);
    }
}
