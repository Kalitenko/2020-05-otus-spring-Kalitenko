package ru.otus.spring.dao;

import ru.otus.spring.domain.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class TaskDaoCsv implements TaskDao {

    private final InputStream inputStream;
    private final String delimiter;
    private final BufferedReader reader;

    public TaskDaoCsv(InputStream inputStream, String delimiter) {
        this.inputStream = inputStream;
        this.delimiter = delimiter;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
    }

    private List<Task> readFromStream() {

        List<String> strings = new ArrayList<>();

        try {
            while (reader.ready()) {
                strings.add(reader.readLine());
            }
        } catch (IOException e) {
            throw new InvalidInputStreamException();
        }

        return createTests(strings);
    }

    private List<Task> createTests(List<String> strings) {
        return strings.stream().map(x -> {
            String[] split = x.split(delimiter, 3);
            return new Task(split[0], split[1], Arrays.asList(split[2].split(delimiter)));
        }).collect(Collectors.toList());
    }

    @Override
    public List<Task> getAll() {

        return readFromStream();

    }
}
