package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;

import java.util.Collection;

/**
 * Сервис для фильтрации пользователей по имени и отображения их в сетке.
 * Предоставляет функционал для поиска пользователей по имени и отображения результатов в интерфейсе.
 */
@Service
public class UsersFilterService {

    private UserRepository userRepository;
    private Grid<User> grid;
    private UserService userService;

    @Autowired
    public UsersFilterService(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    public void setGrid(Grid<User> grid) {
        this.grid = grid;
    }


    public long showUser(String name) {
        long startTime = System.nanoTime();
        if (name.isEmpty()) {
            grid.setItems(userService.getAllUsers());
        }
        else {
            grid.setItems(userService.getByName(name));
        }
        long endTime = System.nanoTime();
        long duration = (endTime - startTime) / 1000000;
        return duration;
    }
}
