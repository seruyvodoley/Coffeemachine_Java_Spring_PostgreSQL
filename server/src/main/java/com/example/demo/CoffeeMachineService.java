package com.example.demo;

import com.example.demo.CoffeeMachine;
import com.example.demo.CoffeeMachineRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для работы с кофемашинами.
 */
@Service
@Transactional(readOnly = true)
public class CoffeeMachineService {

    private final CoffeeMachineRepository repository;
    @Autowired
    private CoffeeMachineControl coffeeMachineControl;

    /**
     * Конструктор для внедрения зависимости репозитория кофемашин.
     *
     * @param repository Репозиторий для кофемашин
     */
    @Autowired
    public CoffeeMachineService(CoffeeMachineRepository repository) {
        this.repository = repository;
    }

    /**
     * Получает все записи о кофемашинах.
     *
     * @return Список всех кофемашин
     */
    public List<CoffeeMachine> findAll() {
        return repository.findAll();
    }

    /**
     * Находит кофемашину по идентификатору.
     *
     * @param id Идентификатор кофемашины
     * @return Найденная кофемашина или null
     */
    public CoffeeMachine findOne(int id) {
        return repository.findById(id).orElse(null);
    }

    /**
     * Сохраняет новую запись о кофемашине.
     *
     * @param coffeeMachine Кофемашина для сохранения
     */
    @Transactional
    public void save(CoffeeMachine coffeeMachine) {
        repository.save(coffeeMachine);
    }

    /**
     * Обновляет информацию о кофемашине.
     *
     * @param id            Идентификатор кофемашины
     * @param coffeeMachine Кофемашина для обновления
     */
    @Transactional
    public void update(int id, CoffeeMachine coffeeMachine) {
        coffeeMachine.setId(id);
        repository.save(coffeeMachine);
    }

    /**
     * Удаляет кофемашину по идентификатору.
     *
     * @param id Идентификатор кофемашины для удаления
     */
    @Transactional
    public void delete(int id) {
        repository.deleteById(id);
    }

    /**
     * Ищет кофемашины по бренду и модели.
     *
     * @param brand     Бренд кофемашины для поиска
     * @param modelName Модель кофемашины для поиска
     * @return Список кофемашин, соответствующих указанным критериям
     */
    public List<CoffeeMachine> searchByBrandAndModel(String brand, String modelName) {
        if (brand != null && !brand.isEmpty()) {
            return repository.findByBrandContains(brand);
        } else if (modelName != null && !modelName.isEmpty()) {
            return repository.findByModelContains(modelName);
        } else {
            return List.of(); // Возвращаем пустой список, если ни один из параметров не задан
        }
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

    public boolean doesNotExist(int id) {
        return !repository.existsById(id);
    }

    @Transactional
    public void buy(int id) throws IllegalArgumentException {
        CoffeeMachine coffeeMachine = repository.findById(id).orElse(null);
        if(coffeeMachine != null){
            if(coffeeMachine.getQuantity() > 0){
                coffeeMachine.setQuantity(coffeeMachine.getQuantity() - 1);
            }
            else{
                throw new IllegalArgumentException("No coffeemachines to buy");
            }
        }
        else {
            throw new IllegalArgumentException("This coffeemachine item does not exist");
        }
    }

}

