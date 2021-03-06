package ru.otus.spring.domain;

import java.util.List;

public class Task {

    private String question;
    private String rightAnswer;
    private List<String> answers;

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<String> getAnswers() {
        return answers;
    }

    public void setAnswers(List<String> answers) {
        this.answers = answers;
    }

    public String getRightAnswer() {
        return rightAnswer;
    }

    public void setRightAnswer(String rightAnswer) {
        this.rightAnswer = rightAnswer;
    }

    public Task(String question, String rightAnswer, List<String> answers) {
        this.question = question;
        this.rightAnswer = rightAnswer;
        this.answers = answers;
    }

    @Override
    public String toString() {
        return "Task{" +
                "question='" + question + '\'' +
                ", rightAnswer='" + rightAnswer + '\'' +
                ", answers=" + answers +
                '}';
    }
}
