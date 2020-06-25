package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.io.Resource;
import ru.otus.spring.dao.TaskDao;
import ru.otus.spring.dao.TaskDaoCsv;
import ru.otus.spring.service.*;

import java.io.InputStream;
import java.io.PrintStream;

@Configuration
@PropertySource("classpath:application.properties")
public class AppConfig {

    @Bean
    public TaskDao taskDao(@Value("classpath:tests.csv") Resource resource, @Value("${delimiter}") String delimiter) {
        return new TaskDaoCsv(resource, delimiter);
    }

    @Bean
    public TaskService taskService(@Qualifier("taskDao") TaskDao dao) {
        return new TaskServiceImpl(dao);
    }

    @Bean
    public ConsoleService consoleService(PrintStream output, InputStream input) {
        return new ConsoleServiceImpl(output, input);
    }

    @Bean
    public TestingService testingService(TaskService taskService, ConsoleService consoleService,
                                         @Value("#{ T(java.lang.Integer).parseInt('${numberOfRightAnswers}')}")
                                                 Integer numberOfRightAnswers,
                                         @Value("#{ T(java.lang.Boolean).valueOf('${withClues}')}")
                                                 Boolean withClues) {
        return new TestingServiceImpl(taskService, consoleService, numberOfRightAnswers, withClues);
    }

}
