package com.example.rest;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

/**
 * Запускает клиентскую часть приложения для кофемашин
 */
@SpringBootApplication
public class RestApplication {

    private RestTemplate restTemplate;

    /**
     * Точка входа в приложение
     * @param args аргументы командной строки
     */
    public static void main(String[] args) {
        SpringApplication.run(RestApplication.class, args);
    }

    /**
     * Шаблон для работы с REST API
     * @return REST шаблон
     */
    @Bean
    public RestTemplate restTemplate() {
        this.restTemplate = new RestTemplate();
        return this.restTemplate;
    }

    /**
     * Получает все кофемашины от сервера
     */
    public void createGetRequest(){
        String url = "http://localhost:8090/api/coffee-machines";
        ResponseEntity<CoffeeMachine[]> response = restTemplate.getForEntity(url, CoffeeMachine[].class);
        System.out.println("Response Code: " + response.getStatusCode());
        System.out.println("Response headers:");
        System.out.println(response.getHeaders());
        // Получаем массив объектов CoffeeMachine из ответа
        CoffeeMachine[] coffeeMachines = response.getBody();
        System.out.println("Response body:");
        // Печатаем каждую кофемашину
        if (coffeeMachines != null) {
            for (CoffeeMachine machine : coffeeMachines) {
                System.out.println(machine);
            }
        }
    }

    /**
     * Получает кофемашину от сервера по индексу
     * @param id индекс запрашиваемой кофемашины
     */
    public void createGetByIDRequest(Integer id){
        String url = "http://localhost:8090/api/coffee-machines/" + id;
        try {
            ResponseEntity<CoffeeMachine> response = restTemplate.getForEntity(url, CoffeeMachine.class);
            System.out.println("Response Code: " + response.getStatusCode());
            System.out.println("Response headers:");
            System.out.println(response.getHeaders());
            System.out.println("Response body:");
            System.out.println(response.getBody());
        }
        catch (HttpClientErrorException e) {
            System.out.println("Response Code: " + e.getStatusCode());
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("CoffeeMachine with id " + id + " not found");
            } else {
                throw e;  // rethrow the exception if it's not a 404 error
            }
        }
    }

    /**
     * Отправляет кофемашину на сервер
     * @param coffeeMachine новая кофемашина
     */
    public void createPostRequest(CoffeeMachine coffeeMachine){
        String url = "http://localhost:8090/api/coffee-machines";
        ObjectMapper mapper = new ObjectMapper();
        try{
            String coffeeMachineJson = mapper.writeValueAsString(coffeeMachine);
            // Устанавливаем заголовки
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Создаем новый HttpEntity
            HttpEntity<String> entity = new HttpEntity<>(coffeeMachineJson, headers);
            // Отправляем запрос
            ResponseEntity<CoffeeMachine> response = restTemplate.postForEntity(url, entity, CoffeeMachine.class);
            System.out.println("Response Status: " + response.getStatusCode());
            System.out.println("Response headers:");
            System.out.println(response.getHeaders());
            System.out.println("Response Body: " + response.getBody());
        }
        catch (JsonProcessingException e){
            System.out.println("Impossible to process JSON file");
        }
    }

    /**
     * Отправляет модифицированную кофемашину на сервер
     * @param id индекс заменяемой кофемашины
     * @param coffeeMachine изменённая кофемашина
     */
    public void createPutRequest(Integer id, CoffeeMachine coffeeMachine){
        String url = "http://localhost:8090/api/coffee-machines/" + id;
        ObjectMapper mapper = new ObjectMapper();
        try{
            String coffeeMachineJson = mapper.writeValueAsString(coffeeMachine);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            // Создаем новый HttpEntity
            HttpEntity<String> entity = new HttpEntity<>(coffeeMachineJson, headers);
            try{
                restTemplate.put(url, entity);
                System.out.println("Successfully edited an object\n" + coffeeMachine);
            }
            catch (HttpClientErrorException e) {
                if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                    System.out.println("CoffeeMachine with id " + id + " not found");
                } else {
                    throw e;  // rethrow the exception if it's not a 404 error
                }
            }
        }
        catch(JsonProcessingException e){
            System.out.println("Impossible to process JSON file");
        }
    }

    /**
     * Запрашивает удаление кофемашины на сервере
     * @param id индекс удаляемой кофемашины
     */
    public void createDeleteRequest(Integer id){
        try {
            restTemplate.delete("http://localhost:8090/api/coffee-machines/delete/" + id);
            System.out.println("CoffeeMachine with id " + id + " deleted successfully");
        } catch (HttpClientErrorException e) {
            if (e.getStatusCode() == HttpStatus.NOT_FOUND) {
                System.out.println("CoffeeMachine with id " + id + " deleted successfully");
            } else {
                throw e;
            }
        }
    }

    /**
     * Запускает работу приложения в командной строке
     * @return CommandLineRunner
     */
    @Bean
    public CommandLineRunner run() {
        return args -> {
            System.out.println("\n------------------------------ Getting all coffee machines -----------------------------");
            createGetRequest();
            System.out.println("\n--------------------------- Getting coffee machine by ID ---------------------------");
            createGetByIDRequest(4);
            System.out.println("\n--------------------------- Posting new coffee machine ---------------------------");
            CoffeeMachine newMachine = new CoffeeMachine("Delonghi", "Magnifica", 20000, 1500, 1000, 250);
            createPostRequest(newMachine);
            System.out.println("\n--------------------------- Editing coffee machine by ID ---------------------------");
            CoffeeMachine machine = new CoffeeMachine("Saeco", "Xelsis", 35000, 2000, 1500, 300);
            machine.setId(1);
            createPutRequest(1, machine);
            System.out.println("\n--------------------------- Deleting coffee machine by ID ---------------------------");
            createDeleteRequest(11);
        };
    }
}
