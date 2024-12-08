package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.UI;

/**
 * Сервис для навигации по страницам в приложении с использованием Vaadin.
 * Позволяет перенаправлять пользователя на различные страницы.
 */
public class PagesNavigator {
    /**
     * Навигация на указанную страницу.
     * Перенаправляет пользователя на указанную страницу в приложении.
     *
     * @param page имя страницы, на которую нужно перейти
     */
    public static void navigateTo(String page) {
        UI.getCurrent().navigate(page);
    }
}
