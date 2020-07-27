package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.domain.Task;

import java.util.Arrays;

@ExtendWith(MockitoExtension.class)
public class TestingServiceImplTest {

    private Task task() {
        return new Task(
                "question",
                "answer 3",
                Arrays.asList("answer 1", "answer 2", "answer 3")
        );
    }

    @Mock
    private ConsoleService consoleService;

    @Mock
    private TaskService taskService;

    private TestingServiceImpl testingService;

    @BeforeEach
    void setUp() {
        testingService = new TestingServiceImpl(taskService, consoleService, 3, true);
    }

    @Test
    void checkAnswer_shouldReturnTrue() {
        Task task = task();

    }


}
