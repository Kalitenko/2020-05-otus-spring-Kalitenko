package ru.otus.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;
import ru.otus.spring.service.ConsoleServiceImpl;
import ru.otus.spring.service.TaskServiceImpl;

public class TestingApp {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("/spring-context.xml");

        TaskServiceImpl testService = context.getBean("testService", TaskServiceImpl.class);
        ConsoleServiceImpl consoleService = (ConsoleServiceImpl) context.getBean("consoleService");
        testService.getAll().forEach(consoleService::print);
        context.close();

    }
}
