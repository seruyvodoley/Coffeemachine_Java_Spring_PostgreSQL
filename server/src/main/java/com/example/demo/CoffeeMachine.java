package com.example.demo;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;

/**
 * Модель для кофемашины
 */
@Entity
@Table(name = "coffee_machines")
public class CoffeeMachine {

    /**
     * Идентификатор кофемашины
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private int id;

    /**
     * Бренд кофемашины
     */
    @Size(min = 2, max = 255, message = "Длина бренда должна быть от 2 до 255 символов")
    @Column(name = "brand")
    private String brand;

    /**
     * Модель кофемашины
     */
    @Size(min = 2, max = 255, message = "Длина модели должна быть от 2 до 255 символов")
    @Column(name = "model")
    private String model;

    /**
     * Цена кофемашины
     */
    @Min(value = 0, message = "Цена не может быть отрицательной")
    @Column(name = "price")
    private double price;

    /**
     * Ёмкость резервуара для воды (в миллилитрах)
     */
    @Min(value = 0, message = "Ёмкость резервуара для воды не может быть отрицательной")
    @Column(name = "water_tank_capacity")
    private int waterTankCapacity;

    /**
     * Ёмкость резервуара для молока (в миллилитрах)
     */
    @Min(value = 0, message = "Ёмкость резервуара для молока не может быть отрицательной")
    @Column(name = "milk_tank_capacity")
    private int milkTankCapacity;

    /**
     * Ёмкость контейнера для кофе в зёрнах (в граммах)
     */
    @Min(value = 0, message = "Ёмкость контейнера для кофе не может быть отрицательной")
    @Column(name = "coffee_bean_capacity")
    private int coffeeBeanCapacity;

    /**
     * Число товара
     */
    @Min(value = 0, message = "quantity can not be negative")
    @Column(name = "quantity")
    private int quantity;


    /**
     * Конструктор класса без параметров
     */
    public CoffeeMachine() {
        this.brand = "Default brand";
        this.model = "Default model";
        this.price = 0;
        this.waterTankCapacity = 0;
        this.milkTankCapacity = 0;
        this.coffeeBeanCapacity = 0;
        this.quantity = 1;
    }

    /**
     * Получает идентификатор кофемашины
     *
     * @return идентификатор
     */
    public int getId() {
        return id;
    }

    /**
     * Устанавливает идентификатор кофемашины
     *
     * @param id идентификатор
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Получает бренд кофемашины
     *
     * @return бренд
     */
    public String getBrand() {
        return brand;
    }

    /**
     * Устанавливает бренд кофемашины
     *
     * @param brand бренд
     */
    public void setBrand(String brand) {
        this.brand = brand;
    }

    /**
     * Получает модель кофемашины
     *
     * @return модель
     */
    public String getModel() {
        return model;
    }

    /**
     * Устанавливает модель кофемашины
     *
     * @param model модель
     */
    public void setModel(String model) {
        this.model = model;
    }

    /**
     * Получает цену кофемашины
     *
     * @return цена
     */
    public double getPrice() {
        return price;
    }

    /**
     * Устанавливает цену кофемашины
     *
     * @param price цена
     */
    public void setPrice(double price) {
        this.price = price;
    }

    /**
     * Получает ёмкость резервуара для воды
     *
     * @return ёмкость резервуара для воды
     */
    public int getWaterTankCapacity() {
        return waterTankCapacity;
    }

    /**
     * Устанавливает ёмкость резервуара для воды
     *
     * @param waterTankCapacity ёмкость резервуара для воды
     */
    public void setWaterTankCapacity(int waterTankCapacity) {
        this.waterTankCapacity = waterTankCapacity;
    }

    /**
     * Получает ёмкость резервуара для молока
     *
     * @return ёмкость резервуара для молока
     */
    public int getMilkTankCapacity() {
        return milkTankCapacity;
    }

    /**
     * Устанавливает ёмкость резервуара для молока
     *
     * @param milkTankCapacity ёмкость резервуара для молока
     */
    public void setMilkTankCapacity(int milkTankCapacity) {
        this.milkTankCapacity = milkTankCapacity;
    }

    /**
     * Получает ёмкость контейнера для кофе в зёрнах
     *
     * @return ёмкость контейнера для кофе
     */
    public int getCoffeeBeanCapacity() {
        return coffeeBeanCapacity;
    }

    /**
     * Устанавливает ёмкость контейнера для кофе в зёрнах
     *
     * @param coffeeBeanCapacity ёмкость контейнера для кофе
     */
    public void setCoffeeBeanCapacity(int coffeeBeanCapacity) {
        this.coffeeBeanCapacity = coffeeBeanCapacity;
    }

    /**
     * Создаёт строковое представление объекта
     *
     * @return строковое представление объекта
     */
    @Override
    public String toString() {
        return String.format(
                "ID: %d, Бренд: %s, Модель: %s, Цена: %.2f ₽, Ёмкость воды: %d мл, Ёмкость молока: %d мл, Ёмкость кофе: %d г",
                id, brand, model, price, waterTankCapacity, milkTankCapacity, coffeeBeanCapacity
        );
    }
    /**
     * Получает количество товара
     * @return количество
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Устанавливает количество товара
     * @param quantity количество
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
