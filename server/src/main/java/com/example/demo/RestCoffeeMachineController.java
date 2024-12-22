package com.example.demo;

import com.example.demo.CoffeeMachine;
import com.example.demo.CoffeeMachineService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Контроллер REST API для управления кофемашинами.
 */
@RestController
@RequestMapping("/api/coffee-machines")
public class RestCoffeeMachineController {

    private static final Logger logger = LoggerFactory.getLogger(RestCoffeeMachineController.class);

    private final CoffeeMachineService coffeeMachineService;

    /**
     * Конструктор для RestCoffeeMachineController.
     *
     * @param coffeeMachineService сервис для работы с кофемашинами
     */
    @Autowired
    public RestCoffeeMachineController(CoffeeMachineService coffeeMachineService) {
        this.coffeeMachineService = coffeeMachineService;
    }

    /**
     * Возвращает все записи о кофемашинах.
     *
     * @return список кофемашин
     */
    @GetMapping
    public ResponseEntity<List<CoffeeMachine>> getAll() {
        List<CoffeeMachine> coffeeMachines = coffeeMachineService.findAll();
        return new ResponseEntity<>(coffeeMachines, HttpStatus.OK);
    }

    /**
     * Возвращает кофемашину с заданным ID.
     *
     * @param id идентификатор кофемашины
     * @return кофемашина или статус 404, если не найдена
     */
    @GetMapping("/{id}")
    public ResponseEntity<CoffeeMachine> getById(@PathVariable("id") int id) {
        CoffeeMachine coffeeMachine = coffeeMachineService.findOne(id);
        if (coffeeMachine != null) {
            return new ResponseEntity<>(coffeeMachine, HttpStatus.OK);
        } else {
            logger.error("Coffee machine with id {} not found", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Добавляет новую кофемашину.
     *
     * @param coffeeMachine новая кофемашина
     * @return созданная кофемашина со статусом 201
     */
    @PostMapping
    public ResponseEntity<CoffeeMachine> create(@RequestBody @Valid CoffeeMachine coffeeMachine) {
        coffeeMachineService.save(coffeeMachine);
        return new ResponseEntity<>(coffeeMachine, HttpStatus.CREATED);
    }

    /**
     * Обновляет существующую кофемашину по ID.
     *
     * @param id            идентификатор кофемашины
     * @param coffeeMachine обновленные данные кофемашины
     * @return обновленная кофемашина или статус 404, если не найдена
     */
    @PutMapping("/{id}")
    public ResponseEntity<CoffeeMachine> update(@PathVariable("id") int id, @RequestBody @Valid CoffeeMachine coffeeMachine) {
        if (coffeeMachineService.doesNotExist(id)) {
            logger.error("Attempted to update non-existing coffee machine with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coffeeMachineService.update(id, coffeeMachine);
        return new ResponseEntity<>(coffeeMachine, HttpStatus.OK);
    }

    /**
     * Удаляет кофемашину по ID.
     *
     * @param id идентификатор кофемашины
     * @return статус 204, если удаление прошло успешно, или статус 404, если не найдена
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") int id) {
        if (coffeeMachineService.doesNotExist(id)) {
            logger.error("Attempted to delete non-existing coffee machine with id {}", id);
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        coffeeMachineService.delete(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}
