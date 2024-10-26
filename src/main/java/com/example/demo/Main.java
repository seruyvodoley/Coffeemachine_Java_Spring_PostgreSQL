package com.example.demo;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * Класс, запускающий работу программы
 */
public class Main {

    /**
     * Точка входа в приложение.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        // Создание контекста приложения на основе аннотаций
        ApplicationContext context = new AnnotationConfigApplicationContext("com.example.demo");

        // Получение бина Application из контекста
        Application consoleApplication = context.getBean(Application.class);

        // Запуск приложения
        consoleApplication.run();
    }
}