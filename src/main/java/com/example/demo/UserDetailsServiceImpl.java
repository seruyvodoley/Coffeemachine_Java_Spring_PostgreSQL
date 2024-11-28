package com.example.demo;

import com.example.demo.User;
import com.example.demo.UserRepository;
import com.example.demo.ShopUserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Optional;

/**
 * Сервис для загрузки пользовательских данных из репозитория пользователей
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    /**
     * Репозиторий пользователей
     */
    private final UserRepository userRepository;

    /**
     * Конструктор для UserDetailsServiceImpl
     * @param userRepository Репозиторий пользователей магазина
     */
    @Autowired
    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Получает пользователя по его имени
     * @param username имя пользователя
     * @return ShopUserDetails для пользователя
     * @throws UsernameNotFoundException если пользователь не был найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new UsernameNotFoundException("User not found");
        }
        return new ShopUserDetails(user.get());
    }
}