package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;


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


    public long showUsers(String name) {
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
