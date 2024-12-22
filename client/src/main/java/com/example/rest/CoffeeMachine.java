package com.example.rest;

/**
 * Класс, представляющий кофемашину.
 * Хранит информацию о модели, бренде, цене и ёмкостях контейнеров для воды, молока и кофейных зёрен.
 */
public class CoffeeMachine {
    private int id;  // Идентификатор кофемашины
    private String brand;

    private String model;

    private double price;

    private int waterTankCapacity;

    private int milkTankCapacity;
    private int coffeeBeanCapacity;
    /**
     * Конструктор по умолчанию, инициализирующий поля значениями по умолчанию.
     */
    public CoffeeMachine() {
        this.brand = "Default Brand";
        this.model = "Default Model";
        this.price = 0;
    }

    /**
     * Конструктор с параметрами для создания объекта кофемашины с указанными характеристиками.
     *
     * @param brand              бренд кофемашины
     * @param model              модель кофемашины
     * @param price              цена кофемашины
     * @param waterTankCapacity  ёмкость резервуара для воды (в миллилитрах)
     * @param milkTankCapacity   ёмкость резервуара для молока (в миллилитрах)
     * @param coffeeBeanCapacity ёмкость контейнера для кофейных зёрен (в граммах)
     */
    public CoffeeMachine(String brand, String model, double price, int waterTankCapacity, int milkTankCapacity, int coffeeBeanCapacity) {
        this.brand = brand;
        this.model = model;
        this.price = price;
        this.waterTankCapacity = waterTankCapacity;
        this.milkTankCapacity = milkTankCapacity;
        this.coffeeBeanCapacity = coffeeBeanCapacity;
    }

    // Геттеры и сеттеры для всех полей

    /** Возвращает идентификатор кофемашины. */
    public int getId() { return id; }

    /** Устанавливает идентификатор кофемашины. */
    public void setId(int id) { this.id = id; }

    /** Возвращает бренд кофемашины. */
    public String getBrand() { return brand; }

    /** Устанавливает бренд кофемашины. */
    public void setBrand(String brand) { this.brand = brand; }

    /** Возвращает модель кофемашины. */
    public String getModel() { return model; }

    /** Устанавливает модель кофемашины. */
    public void setModel(String model) { this.model = model; }

    /** Возвращает цену кофемашины. */
    public double getPrice() { return price; }

    /** Устанавливает цену кофемашины. */
    public void setPrice(double price) { this.price = price; }

    /** Возвращает ёмкость резервуара для воды. */
    public int getWaterTankCapacity() { return waterTankCapacity; }

    /** Устанавливает ёмкость резервуара для воды. */
    public void setWaterTankCapacity(int waterTankCapacity) { this.waterTankCapacity = waterTankCapacity; }

    /** Возвращает ёмкость резервуара для молока. */
    public int getMilkTankCapacity() { return milkTankCapacity; }

    /** Устанавливает ёмкость резервуара для молока. */
    public void setMilkTankCapacity(int milkTankCapacity) { this.milkTankCapacity = milkTankCapacity; }

    /** Возвращает ёмкость контейнера для кофейных зёрен. */
    public int getCoffeeBeanCapacity() { return coffeeBeanCapacity; }

    /** Устанавливает ёмкость контейнера для кофейных зёрен. */
    public void setCoffeeBeanCapacity(int coffeeBeanCapacity) { this.coffeeBeanCapacity = coffeeBeanCapacity; }

    /**
     * Возвращает строковое представление объекта кофемашины с описанием её характеристик.
     *
     * @return строка с информацией о кофемашине
     */
    @Override
    public String toString() {
        return String.format("ID: %d, Бренд: %s, Модель: %s, Цена: %.2f руб, Ёмкость воды: %d мл, Ёмкость молока: %d мл, Ёмкость кофейных зёрен: %d г",
                id, brand, model, price, waterTankCapacity, milkTankCapacity, coffeeBeanCapacity);
    }
}
