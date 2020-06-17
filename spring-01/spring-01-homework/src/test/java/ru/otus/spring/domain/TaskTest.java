package ru.otus.spring.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Класс Task")
class TaskTest {

    private Task task;

    @BeforeEach
    void init() {
        task = new Task(
                "question",
                "answer 3",
                Arrays.asList("answer 1", "answer 2", "answer 3")
        );
    }

    @DisplayName("корректно создаётся конструктором")
    @Test
    void shouldHaveCorrectConstructor() {
        assertAll(
                () -> assertEquals("question", task.getQuestion()),
                () -> assertEquals("answer 3", task.getRightAnswer()),
                () -> assertEquals(3, task.getAnswers().size())
        );
    }
}