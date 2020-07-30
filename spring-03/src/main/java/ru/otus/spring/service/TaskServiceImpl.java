package ru.otus.spring.service;

import org.springframework.stereotype.Service;
import ru.otus.spring.dao.TaskDao;
import ru.otus.spring.domain.Task;

import java.util.List;

@Service
public class TaskServiceImpl implements TaskService {

    private final TaskDao dao;

    public TaskServiceImpl(TaskDao dao) {
        this.dao = dao;
    }

    @Override
    public List<Task> getAll() {
        return dao.getAll();
    }
}
