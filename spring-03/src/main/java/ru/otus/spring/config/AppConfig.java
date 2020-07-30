package ru.otus.spring.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.TaskDao;
import ru.otus.spring.dao.TaskDaoCsv;

@Configuration
@PropertySource("classpath:application.yml")
public class AppConfig {

    @Bean
    public TaskDao taskDao(@Value("classpath:tests_#{T(java.util.Locale).getDefault().getLanguage() + '.csv'}") Resource resource,
                           @Value("${delimiter}") String delimiter) {
        return new TaskDaoCsv(resource, delimiter);
    }

    @Bean
    public MessageSource messageSource() {
        var ms = new ReloadableResourceBundleMessageSource();
        ms.setBasename("classpath:/il8n/bundle");
        ms.setDefaultEncoding("UTF-8");
        return ms;
    }

}