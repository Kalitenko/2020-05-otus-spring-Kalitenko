## Домашнее задание 2
#### *Приложение по проведению тестирования студентов (с самим тестированием)*
**Цель:** конфигурировать Spring-приложения современным способом, как это и делается в современном мире 

**Результат:** готовое современное приложение на чистом Spring
#### Описание задания

**Новый функционал:**

Программа должна спросить у пользователя фамилию и имя, спросить 5 вопросов из CSV-файла и вывести результат тестирования.

Выполняется на основе [предыдущего домашнего задания](../spring-01) 

Требования:

1. Переписать конфигурацию в виде Java + Annotation-based конфигурации.
2. Добавить функционал тестирования студента.
3. Добавьте файл настроек для приложения тестирования студентов.
4. В конфигурационный файл можно поместить путь до CSV-файла, количество правильных ответов для зачёта - на Ваше усмотрение.
5. Если Вы пишите интеграционные тесты, то не забудьте добавить аналогичный файл и для тестов.
6. Scanner, PrintStream и другие стандартные типы в контекст класть не нужно!
7. Ввод-вывод на английском языке.
8. Помним, "без фанатизма" :)

#### Реализация
Приложение в Java-based стиле без использования стереотипов

В [application.properties](./src/main/resources/application.properties) заданы настройки приложения

| имя свойства | значение свойства |
| :--- | :---  |
| delimiter | разделитель  |
| numberOfRightAnswers | количество правильных ответов |
| withClues | включение подсказок |

[CSV-файл](./src/main/resources/tests.csv) с вопросами и ответами