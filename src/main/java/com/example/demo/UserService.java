package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 * Сервис для работы с пользователями системы
 */
@Service
@Transactional(readOnly = true)
public class UserService {
    /**
     * Репозиторий для работы с пользователями
     */
    private final UserRepository userRepository;
    /**
     * Кодировщик паролей
     */
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для ShopUserService
     * @param userRepository Репозиторий пользователей магазина
     * @param passwordEncoder    Кодировщик паролей
     */
    @Autowired
    public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Регистрирует нового пользователя.
     * @param user Модель пользователя магазина.
     */
    @Transactional
    public void registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        if (user.getUsername().equals("admin")) {
            user.setStatus("ROLE_ADMIN");
        } else {
            user.setStatus("ROLE_USER");
        }
        userRepository.save(user);
    }

    /**
     * Проверяет наличие пользователя с указанным именем.
     * @param username Имя пользователя для проверки.
     * @return true, если пользователь существует, в противном случае false.
     */
    public boolean hasUsername(String username) {
        return userRepository.findByUsername(username).isPresent();
    }
}