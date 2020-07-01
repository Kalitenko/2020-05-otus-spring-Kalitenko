package ru.otus.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import ru.otus.junit.domain.Person;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("Класс Person")
class PersonTest {

    private Person person() {
        return new Person(42, "Ivan");
    }

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {

        assertThat(person())
                .extracting(Person::getAge, Person::getName)
                .containsExactly(42, "Ivan");

    }

    @DisplayName("должен увеличивать возраст при вызове birthDay")
    @Test
    void shouldCorrectlyAgeIncrement() {
        Person person = person();
        person.birthDay();

        assertThat(person.getAge()).as("неправильна работа метода birthDay").isEqualTo(43);
    }
}
