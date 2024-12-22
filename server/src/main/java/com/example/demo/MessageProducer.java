package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Класс отправителя сообщений
 */
@Component
public class MessageProducer {
    private static final Logger logger = LoggerFactory.getLogger(MessageProducer.class);

    private final JmsTemplate jmsTemplate;
    private final String queueName;

    /**
     * Конструктор отправителя сообщений
     * @param jmsTemplate шаблон для работы с jms
     * @param queueName название jms очереди
     */
    @Autowired
    public MessageProducer(JmsTemplate jmsTemplate, @Value("${queue.name}") String queueName) {
        this.jmsTemplate = jmsTemplate;
        this.queueName = queueName;
    }

    /**
     * Отправляет сообщение в очередь
     * @param message строка с сообщением
     */
    public void sendMessage(String message) {
        try {
            logger.info("Отправка сообщения в очередь '{}': {}", queueName, message);
            jmsTemplate.convertAndSend(queueName, message);
            logger.info("Сообщение успешно отправлено.");
        } catch (Exception e) {
            logger.error("Ошибка при отправке сообщения в очередь '{}': {}", queueName, e.getMessage(), e);
        }
    }
}