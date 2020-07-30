package ru.otus.spring.service;

import org.springframework.lang.Nullable;

public interface LocalizationService {

    String getMessage(String code, @Nullable Object[] args);

    String getMessage(String code);

}
