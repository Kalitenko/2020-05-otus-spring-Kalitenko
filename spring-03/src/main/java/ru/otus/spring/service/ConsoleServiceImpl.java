package ru.otus.spring.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import ru.otus.spring.dao.IOStreamException;
import ru.otus.spring.domain.Task;

import java.io.*;
import java.util.List;

@Service
public class ConsoleServiceImpl implements ConsoleService {

    private final PrintStream printStream;
    private final BufferedReader reader;
    private final LocalizationService localizationService;

    public ConsoleServiceImpl(@Value("#{T(java.lang.System).out}") PrintStream printStream,
                              @Value("#{T(java.lang.System).in}") InputStream inputStream,
                              LocalizationService localizationService) {
        this.printStream = printStream;
        this.reader = new BufferedReader(new InputStreamReader(inputStream));
        this.localizationService = localizationService;
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
        printStream.println(localizationService.getMessage("enter.name"));
        String fullName;
        try {
            fullName = reader.readLine();

        } catch (IOException e) {
            throw new IOStreamException();
        }
        if (!isValidFullName(fullName)) {
            fullName = requestFullName();
        }
        return fullName;
    }

    private boolean isValidFullName(String fullName) {
        if (null == fullName)
            return false;

        String[] s = fullName.split("\\s+");
        if (s.length <= 1) {
            printStream.println(localizationService.getMessage("name.surname"));
            return false;
        }
        if (s.length > 2) {
            printStream.println(localizationService.getMessage("interesting.name"));
        }
        return true;
    }

    @Override
    public String readAnswer() {
        String answer;
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
            printStream.println(localizationService.getMessage("choose.answer") + "\n"
                    + String.join(" ", answers) + " "
                    + localizationService.getMessage("clue") + rightAnswer);
        } else {
            printStream.println(localizationService.getMessage("answers") + answers);
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
