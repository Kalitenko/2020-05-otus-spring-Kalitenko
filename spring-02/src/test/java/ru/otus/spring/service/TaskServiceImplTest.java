package ru.otus.spring.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.otus.spring.dao.TaskDao;
import ru.otus.spring.domain.Task;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class TaskServiceImplTest {

    @Mock
    private TaskDao dao;

    private TaskServiceImpl taskService;

    @BeforeEach
    void setUp() {
        taskService = new TaskServiceImpl(dao);
    }

    @Test
    void getAll_shouldReturn3Tasks() {
        given(dao.getAll()).willReturn(Arrays.asList(
                new Task("question", "answer 1", Arrays.asList("answer 1", "answer 2", "answer 3")),
                new Task("question", "answer 2", Arrays.asList("answer 1", "answer 2", "answer 3")),
                new Task("question", "answer 3", Arrays.asList("answer 1", "answer 2", "answer 3"))
        ));
        assertThat(taskService.getAll().size()).isEqualTo(3);
        verify(dao).getAll();
    }


}