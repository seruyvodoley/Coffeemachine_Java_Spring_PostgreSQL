package com.example.demo;

import org.springframework.boot.SpringApplication;
import com.example.demo.MessageListener;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * Класс, запускающий работу программы
 */
@SpringBootApplication
public class Application {
    /**
     * Точка входа в приложение.
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
        ConfigurableApplicationContext context = SpringApplication.run(Application.class, args);
        MessageListener messageReceiver = context.getBean(MessageListener.class);
        messageReceiver.startReceivingMessages();
    }
}