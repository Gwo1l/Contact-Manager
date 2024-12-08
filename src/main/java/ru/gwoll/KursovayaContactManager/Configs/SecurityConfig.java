package ru.gwoll.KursovayaContactManager.Configs;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.gwoll.KursovayaContactManager.Presenters.LoginPresenter;


@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    /**
     * Конфигурация безопасности для веб-приложения, основанного на Vaadin.
     * Использует Spring Security для настройки безопасности и аутентификации.
     */
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginPresenter.class);
    }

    /**
     * Bean для кодирования паролей с использованием алгоритма BCrypt.
     * Этот метод создаёт экземпляр BCryptPasswordEncoder, который будет использоваться
     * для хэширования и проверки паролей пользователей.
     *
     * @return экземпляр PasswordEncoder, настроенный на использование BCrypt
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
