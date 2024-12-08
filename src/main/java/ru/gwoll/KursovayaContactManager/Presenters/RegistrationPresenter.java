package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gwoll.KursovayaContactManager.Services.MyUserDetailsService;
import ru.gwoll.KursovayaContactManager.Services.RegistrationFormBinder;


/**
 * Презентер для страницы регистрации нового пользователя.
 * Этот класс обрабатывает отображение формы регистрации и привязку данных формы к сервису для регистрации пользователя.
 */
@Route("registration")
@PageTitle("Регистрация")
@AnonymousAllowed
public class RegistrationPresenter extends VerticalLayout {
    private H1 registrationTitle;

    /**
     * Конструктор, который инициализирует компоненты страницы регистрации.
     * Создаёт форму регистрации и настраивает её привязку с сервисом для обработки данных.
     *
     * @param userDetailsService Сервис для обработки данных пользователя
     */
    @Autowired
    public RegistrationPresenter(MyUserDetailsService userDetailsService) {
        registrationTitle = new H1("Регистрация");
        RegistrationForm registrationForm = new RegistrationForm();

        setHorizontalComponentAlignment(Alignment.CENTER, registrationTitle, registrationForm);

        add(registrationTitle, registrationForm);

        RegistrationFormBinder registrationFormBinder = new RegistrationFormBinder(registrationForm, userDetailsService);
        registrationFormBinder.addBindingAndValidation();
    }

}
