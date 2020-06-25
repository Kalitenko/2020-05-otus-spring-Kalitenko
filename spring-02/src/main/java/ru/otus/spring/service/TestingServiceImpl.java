package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.util.concurrent.atomic.AtomicInteger;

public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final ConsoleService consoleService;
    private final Integer numberOfRightAnswers;
    private final boolean withClues;

    public TestingServiceImpl(TaskService taskService, ConsoleService consoleService, Integer numberOfRightAnswers, boolean withClues) {
        this.taskService = taskService;
        this.consoleService = consoleService;
        this.numberOfRightAnswers = numberOfRightAnswers;
        this.withClues = withClues;
    }

    @Override
    public void toTest() {
        startTest();
        testResult(testing(withClues), numberOfRightAnswers);
        consoleService.closeInputStream();
    }

    private void startTest() {
        consoleService.requestFullName();
    }

    private int testing(boolean withClues) {
        AtomicInteger countOfRightAnswer = new AtomicInteger();
        taskService.getAll().forEach(x -> {
            consoleService.print(x.getQuestion());
            consoleService.printAnswers(x, withClues);
            String answer = consoleService.readAnswer();
            if (checkAnswer(x, answer)) {
                countOfRightAnswer.getAndIncrement();
            }
        });
        return countOfRightAnswer.get();
    }

    private boolean checkAnswer(Task task, String answer) {
        return task.getRightAnswer().equals(answer);
    }

    private void testResult(int count, int numberOfRightAnswers) {
        if (numberOfRightAnswers <= count) {
            consoleService.print("Success!");
        } else {
            consoleService.print("Failed");
        }
    }

}
