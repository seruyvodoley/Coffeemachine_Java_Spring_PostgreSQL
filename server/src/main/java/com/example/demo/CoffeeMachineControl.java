package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import static org.springframework.jdbc.support.JdbcUtils.isNumeric;

/**
 * Класс для управления взаимодействием с базой данных кофемашин.
 * Реализует операции добавления, редактирования, удаления, поиска и получения списка кофемашин.
 */
@Repository
public class CoffeeMachineControl {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    /**
     * Добавляет новую кофемашину в базу данных.
     *
     * @param coffeeMachine объект кофемашины, содержащий данные для добавления
     */
    public void addCoffeeMachine(CoffeeMachine coffeeMachine) {
        String sql = "INSERT INTO coffee_machines (brand, model, price, water_tank_capacity, milk_tank_capacity, coffee_bean_capacity) VALUES (?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, coffeeMachine.getBrand(), coffeeMachine.getModel(), coffeeMachine.getPrice(),
                coffeeMachine.getWaterTankCapacity(), coffeeMachine.getMilkTankCapacity(), coffeeMachine.getCoffeeBeanCapacity());
    }

    public List<CoffeeMachine> searchCoffeeMachine(String field, String value) {
        String sql = "SELECT * FROM coffee_machines WHERE LOWER(" + field + ") LIKE ?";
        return jdbcTemplate.query(connection -> {
            var ps = connection.prepareStatement(sql);
            ps.setString(1, "%" + value.toLowerCase() + "%");
            return ps;
        }, new CoffeeMachineRowMapper());
    }


    /**
     * Получает список всех кофемашин из базы данных.
     *
     * @return список всех кофемашин
     */
    public List<CoffeeMachine> getAllCoffeeMachines() {
        String sql = "SELECT * FROM coffee_machines";
        return jdbcTemplate.query(sql, new CoffeeMachineRowMapper());
    }

    /**
     * Обновляет информацию о кофемашине в базе данных.
     *
     * @param id             идентификатор кофемашины, которую нужно обновить
     * @param updatedMachine объект кофемашины с обновлёнными данными
     */
    public void updateCoffeeMachine(Integer id, CoffeeMachine updatedMachine) {
        String sql = "UPDATE coffee_machines SET brand=?, model=?, price=?, water_tank_capacity=?, milk_tank_capacity=?, coffee_bean_capacity=? WHERE id=?";
        jdbcTemplate.update(sql, updatedMachine.getBrand(), updatedMachine.getModel(), updatedMachine.getPrice(),
                updatedMachine.getWaterTankCapacity(), updatedMachine.getMilkTankCapacity(), updatedMachine.getCoffeeBeanCapacity(), id);
    }

    /**
     * Удаляет кофемашину из базы данных по идентификатору.
     *
     * @param id идентификатор кофемашины для удаления
     */
    public void deleteCoffeeMachine(Integer id) {
        String sql = "DELETE FROM coffee_machines WHERE id=?";
        jdbcTemplate.update(sql, id);
    }

    /**
     * Осуществляет поиск кофемашин в базе данных по указанному полю и значению.
     *
     * @param field поле для поиска (например, "brand" или "model")
     * @param value значение, по которому осуществляется поиск
     * @return список кофемашин, соответствующих критерию поиска
     */


    /**
     * Внутренний класс для преобразования строк базы данных в объекты `CoffeeMachine`.
     */
    private static class CoffeeMachineRowMapper implements RowMapper<CoffeeMachine> {
        @Override
        public CoffeeMachine mapRow(ResultSet rs, int rowNum) throws SQLException {
            CoffeeMachine coffeeMachine = new CoffeeMachine();
            coffeeMachine.setId(rs.getInt("id"));
            coffeeMachine.setBrand(rs.getString("brand"));
            coffeeMachine.setModel(rs.getString("model"));
            coffeeMachine.setPrice(rs.getInt("price"));
            coffeeMachine.setWaterTankCapacity(rs.getInt("water_tank_capacity"));
            coffeeMachine.setMilkTankCapacity(rs.getInt("milk_tank_capacity"));
            coffeeMachine.setCoffeeBeanCapacity(rs.getInt("coffee_bean_capacity"));
            return coffeeMachine;
        }
    }
}
