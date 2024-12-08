package ru.gwoll.KursovayaContactManager.Services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.Role;
import ru.gwoll.KursovayaContactManager.Entities.User;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.NoSuchElementException;


/**
 * Сервис для загрузки информации о пользователе для аутентификации и авторизации.
 * Реализует интерфейс UserDetailsService из Spring Security.
 * Используется для работы с данными пользователя в системе.
 */
@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class MyUserDetailsService implements UserDetailsService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Конструктор для инициализации репозиториев.
     *
     * @param userRepository репозиторий для работы с пользователями
     * @param passwordEncoder кодировщик паролей
     */
    @Autowired
    public MyUserDetailsService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }


    /**
     * Загружает данные пользователя по имени (username).
     * Если пользователь не найден, выбрасывает исключение UsernameNotFoundException.
     *
     * @param username имя пользователя
     * @return объект UserDetails с данными пользователя
     * @throws UsernameNotFoundException если пользователь не найден
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        try {
            User userFromDB = userRepository.findByName(username).getFirst();
            return new org.springframework.security.core.userdetails.User(
                            userFromDB.getName(),
                            userFromDB.getPassword(),
                            mapRoles(userFromDB));
        }
        catch (Exception e) {
            throw new UsernameNotFoundException("User not found");
        }
    }

    private Collection<GrantedAuthority> mapRoles(User user)
    {
        List<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()));
        return collect;
    }

    public boolean addUser(User user) {
        try {
            User userFromDb = userRepository.findByName(user.getName()).getFirst();
            return false;
        }
        catch (NoSuchElementException e) {
            user.setPassword(passwordEncoder.encode(user.getPassword()));
            user.setRole(Role.USER);
            userRepository.save(user);

            return true;
        }
    }
}
