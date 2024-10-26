package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Сервисный класс для управления кофемашинами.
 * Предоставляет методы для добавления, получения, обновления, удаления и поиска кофемашин,
 * обращаясь к слою данных через CoffeeMachineControl.
 */
@Service
public class CoffeeMachineService {

    @Autowired
    private CoffeeMachineControl coffeeMachineControl;

    /**
     * Добавляет новую кофемашину, используя метод слоя данных.
     *
     * @param coffeeMachine объект кофемашины, содержащий данные для добавления
     */
    public void addCoffeeMachine(CoffeeMachine coffeeMachine) {
        coffeeMachineControl.addCoffeeMachine(coffeeMachine);
    }

    /**
     * Получает список всех кофемашин из слоя данных.
     *
     * @return список всех кофемашин
     */
    public List<CoffeeMachine> getAllCoffeeMachines() {
        return coffeeMachineControl.getAllCoffeeMachines();
    }

    /**
     * Обновляет информацию о кофемашине, обращаясь к слою данных.
     *
     * @param id             идентификатор кофемашины, которую нужно обновить
     * @param updatedMachine объект кофемашины с обновлёнными данными
     */
    public void updateCoffeeMachine(Integer id, CoffeeMachine updatedMachine) {
        coffeeMachineControl.updateCoffeeMachine(id, updatedMachine);
    }

    /**
     * Удаляет кофемашину по идентификатору, используя метод слоя данных.
     *
     * @param id идентификатор кофемашины для удаления
     */
    public void deleteCoffeeMachine(Integer id) {
        coffeeMachineControl.deleteCoffeeMachine(id);
    }

    /**
     * Выполняет поиск кофемашин по указанному полю и значению.
     *
     * @param field поле для поиска (например, "brand" или "model")
     * @param value значение, по которому осуществляется поиск
     * @return список кофемашин, соответствующих критерию поиска
     */
    public List<CoffeeMachine> searchCoffeeMachine(String field, String value) {
        return coffeeMachineControl.searchCoffeeMachine(field, value);
    }
}
