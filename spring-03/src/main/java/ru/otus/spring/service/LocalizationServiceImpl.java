package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;

import java.util.Locale;

@Service
public class LocalizationServiceImpl implements LocalizationService {

    private final Locale currentLocale;
    private final MessageSource messageSource;

    public LocalizationServiceImpl(@Value("#{T(java.util.Locale).getDefault()}") Locale currentLocale, MessageSource messageSource) {
        this.currentLocale = currentLocale;
        this.messageSource = messageSource;
    }

    @Override
    public String getMessage(String code, Object[] args) {
        return messageSource.getMessage(code, args, currentLocale);
    }

    @Override
    public String getMessage(String code) {
        return getMessage(code, null);
    }

}
