package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.util.List;

public interface ConsoleService {

    void printAll(List<Task> tasks);

    void print(Task task);

    void requestFullName();

}
