package com.example.demo;

import com.example.demo.CoffeeMachine;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Репозиторий для работы с кофемашинами.
 */
@Repository
public interface CoffeeMachineRepository extends JpaRepository<CoffeeMachine, Integer> {

    List<CoffeeMachine> findByBrandContains(String brand);
    List<CoffeeMachine> findByModelContains(String model);
    List<CoffeeMachine> findByBrandContainsAndModelContains(String brand, String model);
}