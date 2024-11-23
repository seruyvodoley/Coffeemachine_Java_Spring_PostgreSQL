package com.example.demo;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Scanner;

/**
 * Основной класс приложения для управления кофемашинами.
 * Обеспечивает пользовательский интерфейс для добавления, редактирования, поиска и удаления кофемашин.
 */
@Component
public class Application {
    private final CoffeeMachineService coffeeMachineService;

    /**
     * Конструктор для инициализации объекта {@link CoffeeMachineService}.
     *
     * @param coffeeMachineService объект сервиса для работы с кофемашинами
     */
    public Application(CoffeeMachineService coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    /**
     * Метод запуска приложения. Ожидает команды пользователя для выполнения действий.
     */
    public void run() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("1 - Добавить новую кофемашину");
            System.out.println("2 - Вывести информацию о кофемашинах");
            System.out.println("3 - Изменить информацию о кофемашине");
            System.out.println("4 - Найти кофемашину");
            System.out.println("5 - Удалить кофемашину");
            System.out.println("6 - Выйти из программы");
            String choice = scanner.nextLine();
            switch (choice) {
                case "1" -> addCoffeeMachine(scanner);
                case "2" -> showAllCoffeeMachines();
                case "3" -> editCoffeeMachine(scanner);
                case "4" -> searchCoffeeMachine(scanner);
                case "5" -> deleteCoffeeMachine(scanner);
                case "6" -> {
                    System.out.println("Выход из программы");
                    return;
                }
                default -> System.out.println("Некорректная команда!");
            }
        }
    }

    /**
     * Получает детали кофемашины от пользователя.
     *
     * @param scanner объект Scanner для ввода данных
     * @return объект {@link CoffeeMachine}, заполненный данными
     */
    private CoffeeMachine getCoffeeMachineDetails(Scanner scanner) {
        System.out.println("Введите бренд:");
        String brand = scanner.nextLine();
        System.out.println("Введите модель:");
        String model = scanner.nextLine();
        System.out.println("Введите цену:");
        int price = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ёмкость для воды (мл):");
        int waterCapacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ёмкость для молока (мл):");
        int milkCapacity = Integer.parseInt(scanner.nextLine());
        System.out.println("Введите ёмкость для кофейных зёрен (г):");
        int beanCapacity = Integer.parseInt(scanner.nextLine());
        return new CoffeeMachine(brand, model, price, waterCapacity, milkCapacity, beanCapacity);
    }

    /**
     * Добавляет новую кофемашину в базу данных.
     *
     * @param scanner объект Scanner для ввода данных
     */
    private void addCoffeeMachine(Scanner scanner) {
        CoffeeMachine coffeeMachine = getCoffeeMachineDetails(scanner);
        coffeeMachineService.addCoffeeMachine(coffeeMachine);
        System.out.println("Кофемашина успешно добавлена!");
    }

    /**
     * Выводит все кофемашины из базы данных.
     */
    private void showAllCoffeeMachines() {
        List<CoffeeMachine> machines = coffeeMachineService.getAllCoffeeMachines();
        if (machines.isEmpty()) {
            System.out.println("Кофемашины не найдены.");
        } else {
            machines.forEach(System.out::println);
        }
    }

    /**
     * Редактирует данные выбранной кофемашины.
     *
     * @param scanner объект Scanner для ввода данных
     */
    private void editCoffeeMachine(Scanner scanner) {
        showAllCoffeeMachines();
        System.out.println("Введите ID кофемашины для редактирования:");
        int id = Integer.parseInt(scanner.nextLine());
        CoffeeMachine updatedMachine = getCoffeeMachineDetails(scanner);
        coffeeMachineService.updateCoffeeMachine(id, updatedMachine);
        System.out.println("Кофемашина успешно обновлена!");
    }

    /**
     * Удаляет кофемашину из базы данных по идентификатору.
     *
     * @param scanner объект Scanner для ввода данных
     */
    private void deleteCoffeeMachine(Scanner scanner) {
        showAllCoffeeMachines();
        System.out.println("Введите ID кофемашины для удаления:");
        int id = Integer.parseInt(scanner.nextLine());
        coffeeMachineService.deleteCoffeeMachine(id);
        System.out.println("Кофемашина успешно удалена!");
    }

    /**
     * Ищет кофемашины в базе данных по заданному полю и значению.
     *
     * @param scanner объект Scanner для ввода данных
     */
    private void searchCoffeeMachine(Scanner scanner) {
        System.out.println("Введите поле поиска (brand, model):");
        String field = scanner.nextLine();
        System.out.println("Введите значение для поиска:");
        String value = scanner.nextLine();
        List<CoffeeMachine> results = coffeeMachineService.searchCoffeeMachine(field, value);
        if (results.isEmpty()) {
            System.out.println("Совпадения не найдены.");
        } else {
            results.forEach(System.out::println);
        }
    }
}
