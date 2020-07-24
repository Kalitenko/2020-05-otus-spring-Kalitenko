package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.domain.Task;

import java.util.Locale;
import java.util.concurrent.atomic.AtomicInteger;

@Service("testingService")
public class TestingServiceImpl implements TestingService {

    private final TaskService taskService;
    private final ConsoleService consoleService;
    private final int numberOfRightAnswers;
    private final boolean withClues;
    private final MessageSource messageSource;
    @Value("#{T(java.util.Locale).getDefault()}")
    private Locale currentLocale;

    public TestingServiceImpl(TaskService taskService,
                              ConsoleService consoleService,
                              @Value("${numberOfRightAnswers}") int numberOfRightAnswers,
                              @Value("${withClues}") boolean withClues,
                              MessageSource messageSource) {
        this.taskService = taskService;
        this.consoleService = consoleService;
        this.numberOfRightAnswers = numberOfRightAnswers;
        this.withClues = withClues;
        this.messageSource = messageSource;
    }

    @Override
    public void toTest() {
        String name = startTest();
        testResult(testing(withClues), numberOfRightAnswers, name);
        consoleService.closeInputStream();
    }

    private String startTest() {
        return consoleService.requestFullName();
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

    private void testResult(int count, int numberOfRightAnswers, String name) {
        if (numberOfRightAnswers <= count) {
            consoleService.print(messageSource.getMessage("passed", new String[]{name}, currentLocale));
        } else {
            consoleService.print(messageSource.getMessage("failed", new String[]{name}, currentLocale));
        }
    }

}
