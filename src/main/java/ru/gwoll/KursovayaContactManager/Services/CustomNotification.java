package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.notification.NotificationVariant;

/**
 * Класс для отображения кастомных уведомлений в приложении.
 * Уведомления могут быть с различными стилями, определяемыми с помощью темы.
 */
public class CustomNotification {
    /**
     * Показывает уведомление с заданным сообщением и стилем.
     *
     * @param message текст сообщения уведомления
     * @param variant стиль уведомления, например, ERROR, SUCCESS, или OTHER
     */
    public static void showNotification(String message, NotificationVariant variant) {
        Notification.show(message).addThemeVariants(variant);
    }
}
