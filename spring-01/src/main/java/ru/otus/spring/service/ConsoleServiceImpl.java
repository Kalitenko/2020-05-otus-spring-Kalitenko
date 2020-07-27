package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.io.PrintStream;
import java.util.List;

public class ConsoleServiceImpl implements ConsoleService {

    private final PrintStream printStream;

    public ConsoleServiceImpl(PrintStream printStream) {
        this.printStream = printStream;
    }

    @Override
    public void printAll(List<Task> tasks) {
        tasks.forEach(printStream::println);
    }

    @Override
    public void print(Task task) {
        printStream.println(task);
    }

    @Override
    public void requestFullName() {
        printStream.println("Please enter your name and surname");
    }
}
