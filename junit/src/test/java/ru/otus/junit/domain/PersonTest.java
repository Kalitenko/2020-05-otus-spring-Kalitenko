package ru.otus.junit.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Person")
class PersonTest {

    private Person person;

    @BeforeEach
    void init() {
        person = new Person(42, "Ivan");
    }

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        assertAll(
                () -> assertEquals("Ivan", person.getName()),
                () -> assertEquals(42, person.getAge())
        );
    }

    @DisplayName("должен правильно выставлять возраст")
    @Test
    void shouldCorrectlySetAge() {
        person.setAge(42);

        assertEquals(42, person.getAge());
    }

    @DisplayName("должен правильно выставлять имя")
    @Test
    void shouldCorrectlySetName() {
        person.setName("Ivan");

        assertEquals("Ivan", person.getName());
    }

    @DisplayName("должен увеличивать возраст при вызове birthDay")
    @Test
    void shouldCorrectlyAgeIncrement() {

        person.birthDay();

        assertEquals(43, person.getAge());
    }
}
