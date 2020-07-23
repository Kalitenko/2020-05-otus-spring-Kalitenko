package ru.otus.spring;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import ru.otus.spring.service.TestingServiceImpl;

@ComponentScan("ru.otus.spring")
@EnableAspectJAutoProxy
public class TestingApp {
    public static void main(String[] args) {

        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TestingApp.class);
        TestingServiceImpl testingService = context.getBean("testingService", TestingServiceImpl.class);
        testingService.toTest();

    }
}
