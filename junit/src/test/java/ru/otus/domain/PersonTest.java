package ru.otus.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.junit.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

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

        assertThat(person)
                .extracting(Person::getAge, Person::getName)
                .containsExactly(42, "Ivan");

    }

    @DisplayName("должен правильно выставлять возраст")
    @Test
    void shouldCorrectlySetAge() {
        person.setAge(42);

        assertThat(person.getAge()).as("неверный возраст").isEqualTo(42);
    }

    @DisplayName("должен правильно выставлять имя")
    @Test
    void shouldCorrectlySetName() {
        person.setName("Ivan");

        assertThat(person).extracting(Person::getName).as("неверное имя").isEqualTo("Ivan");
    }

    @DisplayName("должен увеличивать возраст при вызове birthDay")
    @Test
    void shouldCorrectlyAgeIncrement() {

        person.birthDay();

        assertThat(person.getAge()).as("неправильна работа метода birthDay").isEqualTo(43);
    }
}
