package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.IOStreamException;
import ru.otus.spring.domain.Task;

import java.io.*;
import java.util.List;
import java.util.Locale;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final PrintStream printStream;
    private final BufferedReader reader;
    private final MessageSource messageSource;
    @Value("#{T(java.util.Locale).getDefault()}")
    private Locale currentLocale;

    public ConsoleServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream printStream,
                              @Value("#{T(java.lang.System).in}") InputStream inputStream,
                              MessageSource messageSource) {
        this.printStream = printStream;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.messageSource = messageSource;
    }

    @Override
    public void printAll(List<Task> tasks) {
        tasks.forEach(printStream::println);
    }

    @Override
    public void print(Task task) {
        printStream.println(task);
    }

    @Override
    public String requestFullName() {
        printStream.println(messageSource.getMessage("enter_name", new String[]{}, currentLocale));
        String fullName = null;
        try {
            fullName = reader.readLine();

        } catch (IOException e) {
            throw new IOStreamException();
        }
        if (!checkFullName(fullName)) {
            fullName = requestFullName();
        }
        return fullName;
    }

    private boolean checkFullName(String fullName) {
        if (null == fullName)
            return false;

        String[] s = fullName.split("\\s+");
        if (s.length <= 1) {
            printStream.println(messageSource.getMessage("name_surname", new String[]{}, currentLocale));
            return false;
        }
        if (s.length > 2) {
            printStream.println(messageSource.getMessage("intresting_name", new String[]{}, currentLocale));
        }
        return true;
    }

    @Override
    public String readAnswer() {
        String answer = null;
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            throw new IOStreamException();
        }
        return answer;
    }

    @Override
    public void print(String string) {
        printStream.println(string);
    }

    @Override
    public void printAnswers(Task task, boolean withClues) {
        List<String> answers = task.getAnswers();
        String rightAnswer = task.getRightAnswer();
        if (withClues) {
            printStream.println(messageSource.getMessage("choose_answer", new String[]{}, currentLocale) + "\n"
                    + String.join(" ", answers) + " "
                    + messageSource.getMessage("clue", new String[]{}, currentLocale) + rightAnswer);
        } else {
            printStream.println(messageSource.getMessage("answers", new String[]{}, currentLocale) + answers);
        }
    }

    @Override
    public void closeInputStream() {
        try {
            reader.close();
        } catch (IOException e) {
            throw new IOStreamException();
        }
    }

}
