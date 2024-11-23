package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/coffee-machines")
public class CoffeeMachineController {

    @Autowired
    private CoffeeMachineService coffeeMachineService;

    // Главная страница
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Страница просмотра всех кофемашин
    @GetMapping("/list")
    public String showAllCoffeeMachines(Model model) {
        List<CoffeeMachine> machines = coffeeMachineService.getAllCoffeeMachines();
        model.addAttribute("machines", machines);
        return "list-coffee-machines";
    }

    // Страница добавления новой кофемашины
    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("coffeeMachine", new CoffeeMachine());
        return "add-coffee-machine";
    }

    @PostMapping("/add")
    public String addCoffeeMachine(@ModelAttribute CoffeeMachine coffeeMachine) {
        coffeeMachineService.addCoffeeMachine(coffeeMachine);
        return "redirect:/coffee-machines/list";
    }

    // Страница редактирования кофемашины
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CoffeeMachine machine = coffeeMachineService.getAllCoffeeMachines()
                .stream()
                .filter(m -> m.getId() == id)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid coffee machine ID"));
        model.addAttribute("coffeeMachine", machine);
        return "edit-coffee-machine";
    }

    @PostMapping("/edit/{id}")
    public String updateCoffeeMachine(@PathVariable("id") int id, @ModelAttribute CoffeeMachine coffeeMachine) {
        coffeeMachineService.updateCoffeeMachine(id, coffeeMachine);
        return "redirect:/coffee-machines/list"; // Перенаправление на список кофемашин после обновления
    }




    // Страница удаления кофемашины
    @GetMapping("/delete/{id}")
    public String deleteCoffeeMachine(@PathVariable("id") int id) {
        coffeeMachineService.deleteCoffeeMachine(id);
        return "redirect:/coffee-machines/list";
    }

    @GetMapping("/search")
    public String searchCoffeeMachines(
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String value,
            Model model) {

        // Если параметры не указаны, отобразить только форму поиска
        if ((field == null || field.isEmpty()) || (value == null || value.isEmpty())) {
            model.addAttribute("machine", null); // Убираем результаты
            model.addAttribute("message", null); // Убираем сообщения
            model.addAttribute("error", null);   // Убираем ошибки
            return "search"; // Переход на страницу формы поиска
        }

        // Выполнение поиска
        List<CoffeeMachine> results = coffeeMachineService.searchCoffeeMachine(field, value);

        // Обработка результатов поиска
        if (results.isEmpty()) {
            model.addAttribute("message", "Кофемашины с указанными параметрами не найдены.");
            model.addAttribute("machine", null);
        } else {
            model.addAttribute("machine", results);
            model.addAttribute("message", null);
        }

        return "search"; // Остаёмся на странице
    }





}
