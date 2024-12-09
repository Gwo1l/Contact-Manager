package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.Div;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.login.LoginForm;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.auth.AnonymousAllowed;

/**
 * Презентер для страницы входа в систему. Обрабатывает отображение формы входа и кнопку для перехода на страницу регистрации.
 * Также обрабатывает ошибку входа, если она передана через параметры запроса.
 */
@Route("login")
@PageTitle("Войти - SocialLibrary")
@AnonymousAllowed
public class LoginPresenter extends VerticalLayout implements BeforeEnterObserver {
    private LoginForm loginForm;
    private Button regButton;
    private Button googleButton;

    /**
     * Конструктор, который инициализирует форму для входа и кнопку для регистрации.
     * Настроены стили и обработчики событий.
     */
    public LoginPresenter() {
        loginForm = new LoginForm();
        regButton = new Button("Зарегистрироваться", VaadinIcon.ENTER.create());
        googleButton = new Button("Войти с помощью Google", VaadinIcon.GOOGLE_PLUS.create());

        setSizeFull();
        setAlignItems(Alignment.CENTER);
        setJustifyContentMode(JustifyContentMode.CENTER);

        loginForm.setAction("login");

        regButton.addClickListener(e -> UI.getCurrent().navigate("registration"));
        googleButton.addClickListener(e -> UI.getCurrent().getPage().executeJs("window.location.href='/oauth2/authorization/google'"));

        add(new H1("Войти в Social Library"),
                new Div("Если вы не зарегистрированы - нажмите Зарегистриоваться"),
                loginForm, regButton, googleButton);
    }

    /**
     * Метод, который вызывается перед загрузкой страницы.
     * Проверяет, если в URL есть параметр "error", то устанавливает ошибку в форме входа.
     *
     * @param event Событие перед входом на страницу
     */
    @Override
    public void beforeEnter(BeforeEnterEvent event) {
        if (event.getLocation().getQueryParameters().getParameters().containsKey("error")) {
            loginForm.setError(true);
        }
    }
}
