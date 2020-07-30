package ru.otus.spring;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import ru.otus.spring.service.TestingServiceImpl;

@SpringBootApplication
public class TestingApp {
    public static void main(String[] args) {
        ApplicationContext context = SpringApplication.run(TestingApp.class);
        TestingServiceImpl testingService = context.getBean("testingService", TestingServiceImpl.class);
        testingService.toTest();
    }
}
