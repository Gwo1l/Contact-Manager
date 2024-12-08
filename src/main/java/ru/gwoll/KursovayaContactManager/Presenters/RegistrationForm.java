package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.HasValueAndElement;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.button.ButtonVariant;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.textfield.PasswordField;
import com.vaadin.flow.component.textfield.TextField;
import jakarta.validation.constraints.NotBlank;

import java.util.stream.Stream;

/**
 * Форма регистрации для ввода данных пользователя, включая имя, пароль, номер телефона и другие поля.
 * Содержит валидацию обязательных полей и кнопку отправки.
 */
public class RegistrationForm extends FormLayout {

    @NotBlank
    private TextField name;
    @NotBlank
    private PasswordField password;
    private PasswordField passwordConfirm;
    private TextField phoneNumber;
    private TextField gender;
    private TextField country;
    private TextField address;

    private Span errorMessageField;

    private Button submitButton;

    /**
     * Конструктор формы регистрации, инициализирует все поля ввода и кнопку отправки.
     */
    public RegistrationForm() {
        name = new TextField("Имя");
        password = new PasswordField("Пароль");
        passwordConfirm = new PasswordField("Повторный ввод пароля");
        phoneNumber = new TextField("Номер телефона");
        gender = new TextField("Пол (M или W)");
        country = new TextField("Страна");
        address = new TextField("Адрес");


        setRequiredIndicatorVisible(name,  password, passwordConfirm, phoneNumber);

        errorMessageField = new Span();

        submitButton = new Button("Регистрация");
        submitButton.addThemeVariants(ButtonVariant.LUMO_PRIMARY);

        phoneNumber.setPlaceholder("+7");

        add(name, password, passwordConfirm, phoneNumber, gender, country, address, errorMessageField, submitButton);

        setMaxWidth("500px");

    }

    public PasswordField getPasswordField() { return password; }

    public PasswordField getPasswordConfirmField() { return passwordConfirm; }

    public Span getErrorMessageField() { return errorMessageField; }

    public Button getSubmitButton() { return submitButton; }

    private void setRequiredIndicatorVisible(HasValueAndElement<?, ?>... components) {
        Stream.of(components).forEach(comp -> comp.setRequiredIndicatorVisible(true));
    }
}
