package ru.otus.junit.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DisplayName("Класс Person")
class PersonTest {

    private Person person() {
        return new Person(42, "Ivan");
    }

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        Person person = person();
        assertAll(
                () -> assertEquals("Ivan", person.getName()),
                () -> assertEquals(42, person.getAge())
        );
    }

    @DisplayName("должен увеличивать возраст при вызове birthDay")
    @Test
    void shouldCorrectlyAgeIncrement() {
        Person person = person();
        person.birthDay();

        assertEquals(43, person.getAge());
    }
}
