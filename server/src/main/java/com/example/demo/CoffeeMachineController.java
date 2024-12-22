package com.example.demo;

import com.example.demo.CoffeeMachine;
import com.example.demo.CoffeeMachineService;
import jakarta.validation.Valid;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.example.demo.MessageListener;
import com.example.demo.MessageProducer;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;



@Controller
@RequestMapping("/coffee-machines")
public class CoffeeMachineController {

    @Autowired
    private CoffeeMachineService coffeeMachineService;

    private final MessageProducer messageProducer;

    /**
     * Логер для вывода ошибок
     */
    private static final Logger logger = LoggerFactory.getLogger(CoffeeMachineController.class);

    // Конструктор для внедрения MessageProducer
    @Autowired
    public CoffeeMachineController(MessageProducer messageProducer) {
        this.messageProducer = messageProducer;
    }

    // Главная страница
    @GetMapping("/")
    public String home() {
        return "index";
    }

    // Страница просмотра всех кофемашин
    @GetMapping("/list")
    public String showAllCoffeeMachines(Model model) {
        List<CoffeeMachine> machines = coffeeMachineService.findAll();
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
    public String addCoffeeMachine(@Valid @ModelAttribute CoffeeMachine coffeeMachine, BindingResult result,
                                   RedirectAttributes redirectAttributes)  {
        if (result.hasErrors()) {
            messageProducer.sendMessage("Error with creating a new coffee machine item");
            return "add-coffee-machine";
        }
        coffeeMachineService.save(coffeeMachine);
        redirectAttributes.addFlashAttribute("message", "Coffee machine successfully added.");
        messageProducer.sendMessage("Added an object: " + coffeeMachine);
        return "redirect:/coffee-machines/list";
    }

    // Страница редактирования кофемашины
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable("id") int id, Model model) {
        CoffeeMachine machine = coffeeMachineService.findOne(id);
        if (machine == null) {
            throw new IllegalArgumentException("Invalid coffee machine ID");
        }
        model.addAttribute("coffeeMachine", machine);
        return "edit-coffee-machine";
    }

    @PostMapping("/edit/{id}")
    public String updateCoffeeMachine(
            @PathVariable("id") int id,
            @Valid @ModelAttribute CoffeeMachine coffeeMachine,
            BindingResult result
    ) {
        if (result.hasErrors()) {
            logger.error("Error when editing an object with id {}", id);
            messageProducer.sendMessage("Error when editing an object with id: " + id);
            return "edit-coffee-machine";
        }
        coffeeMachineService.update(id, coffeeMachine);
        logger.error("Edited coffee machine with id {}", id);
        messageProducer.sendMessage("Updated an object: " + coffeeMachine);
        return "redirect:/coffee-machines/list";
    }

    // Страница удаления кофемашины
    @GetMapping("/delete/{id}")
    public String deleteCoffeeMachine(@PathVariable("id") int id) {
        if (coffeeMachineService.doesNotExist(id)) {
            logger.error("Error when deleting an object with id: {}", id);
            messageProducer.sendMessage("Error when deleting an object with id: " + id);
            throw new IllegalArgumentException("Invalid coffee machine ID");
        }

        coffeeMachineService.delete(id);
        messageProducer.sendMessage("Deleted an object: " + id);
        return "redirect:/coffee-machines/list";
    }

    // Страница покупки кофемашины
    @GetMapping("/buy/{id}")
    public String buy(@PathVariable("id") int id) {
        CoffeeMachine machine = coffeeMachineService.findOne(id);
        if(coffeeMachineService.doesNotExist(id)){
            messageProducer.sendMessage("Error purchasing product with id: " + id);
            return "redirect:/";
        } else {
            try {
                coffeeMachineService.buy(id);
                messageProducer.sendMessage("The product: " + machine + " was successfully purchased");
            } catch (IllegalArgumentException e) {
                messageProducer.sendMessage("The product: " + machine + " was successfully purchased");
            }
            return "redirect:/";
        }
    }

    // Страница поиска кофемашин
    @GetMapping("/search")
    public String searchCoffeeMachines(
            @RequestParam(required = false) String field,
            @RequestParam(required = false) String value,
            Model model) {

        if ((field == null || field.isEmpty()) || (value == null || value.isEmpty())) {
            model.addAttribute("machine", null); // Убираем результаты
            model.addAttribute("message", null); // Убираем сообщения
            model.addAttribute("error", null);   // Убираем ошибки
            return "search"; // Переход на страницу формы поиска
        }

        // Выполнение поиска
        List<CoffeeMachine> results = coffeeMachineService.searchCoffeeMachine(field, value);

        if (results.isEmpty()) {
            logger.error("No coffee machines found");
            model.addAttribute("message", "No coffee machines found with the given parameters.");
            model.addAttribute("machine", null);
        } else {
            model.addAttribute("machine", results);
            model.addAttribute("message", null);
        }

        return "search"; // Остаёмся на странице
    }
}
