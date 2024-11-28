package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserService;
import com.example.demo.UserValidator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

/**
 * Контроллер для работы с пользователями
 */
@Controller
@RequestMapping("/")
public class UserController {
    /**
     * Сервис для рабботы с пользователями
     */
    private final UserService userService;
    /**
     * Валидатор пользователей
     */
    private final UserValidator userValidator;

    /**
     * Внедряет значения параметров
     * @param userService Сервис для рабботы с пользователями
     * @param userValidator Валидатор пользователей
     */
    @Autowired
    public UserController(UserService userService, UserValidator userValidator) {
        this.userService = userService;
        this.userValidator = userValidator;
    }

    /**
     * Выводит форму регестрации пользователя
     * @param user объект пользователя
     * @return Имя представления для отображения
     */
    @GetMapping("/sign-up")
    public String registration(@ModelAttribute("user") User user) {
        return "sign_up";
    }

    /**
     * Производит регестрацию пользователя
     * @param user объект пользователя
     * @param bindingResult результат связывания объекта
     * @return Имя представления для отображения
     */
    @PostMapping("/process_reg")
    public String doReg(@ModelAttribute("user") @Valid User user, BindingResult bindingResult) {
        userValidator.validate(user, bindingResult);
        if (bindingResult.hasErrors()) {
            return "sign_up";
        } else {
            userService.registerUser(user);
            return "redirect:/";
        }
    }

    /**
     * Выводит форму авьоризации представления
     * @return Имя представления для отображения
     */
    @GetMapping("/login")
    public String login() {
        return "login";
    }
}


