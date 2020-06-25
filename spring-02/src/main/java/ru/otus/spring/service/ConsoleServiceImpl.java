package ru.otus.spring.service;

import ru.otus.spring.domain.Task;

import java.io.*;
import java.util.List;

public class ConsoleServiceImpl implements ConsoleService {

    private final PrintStream printStream;
    private final InputStream inputStream;

    public ConsoleServiceImpl(PrintStream printStream, InputStream inputStream) {
        this.printStream = printStream;
        this.inputStream = inputStream;
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
        printStream.println("Please enter your name and surname");
        String fullName = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            fullName = reader.readLine();

        } catch (IOException e) {
            e.printStackTrace();
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
            printStream.println("Name and surname, please");
            return false;
        }
        if (s.length > 2) {
            printStream.println("Ok. Your name is so interesting");
        }
        return true;
    }

    @Override
    public String readAnswer() {
        String answer = null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        try {
            answer = reader.readLine();
        } catch (IOException e) {
            e.printStackTrace();
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
            printStream.println("Choose the correct answer:\n"
                    + String.join(" ", answers)
                    + "Clue: rightAnswer: " + rightAnswer);
        } else {
            printStream.println("answers: " + answers);
        }
    }

    @Override
    public void closeInputStream() {
        try {
            inputStream.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
