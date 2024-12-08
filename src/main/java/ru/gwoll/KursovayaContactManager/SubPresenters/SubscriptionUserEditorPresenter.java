package ru.gwoll.KursovayaContactManager.SubPresenters;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.SubscriptionsRepository;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;
import ru.gwoll.KursovayaContactManager.Services.CustomNotification;
import ru.gwoll.KursovayaContactManager.Services.SubscriptionsService;

/**
 * Презентер для отображения и редактирования информации о пользователе, на которого подписан текущий пользователь.
 * Этот класс управляет отображением информации о подписанном пользователе, его избранных книгах, обзорах и оценках.
 */
@SpringComponent
@UIScope
public class SubscriptionUserEditorPresenter extends VerticalLayout {
    private SubscriptionsRepository subscriptionsRepository;
    private UserRepository userRepository;
    private User subscribedUser;
    private TextField name;
    private Button unsubscibeButton;
    private Binder<User> userBinder;


    @Autowired
    public SubscriptionUserEditorPresenter(SubscriptionsService subscriptionsService,
                                           SubscriptionsRepository subscriptionsRepository,
                                           UserRepository userRepository) {
        this.subscriptionsRepository = subscriptionsRepository;
        this.userRepository = userRepository;

        name = new TextField("Имя");
        name.setReadOnly(true);

        unsubscibeButton = new Button("Отписаться", VaadinIcon.UNLINK.create());

        userBinder = new Binder<>(User.class);
        userBinder.bindInstanceFields(this);

        setHorizontalComponentAlignment(Alignment.CENTER, name, unsubscibeButton);
        add(name, unsubscibeButton);

        unsubscibeButton.addClickListener(e -> {
            subscriptionsService.unsubscribe(subscribedUser);
            CustomNotification.showNotification("Вы отписались", NotificationVariant.LUMO_SUCCESS);
        });

        setSpacing(true);
        setVisible(false);
    }

    public void editSubscription(User user) {
        if (user == null) {
            setVisible(true);
            return;
        }

        if (user.getId() != null) {
            this.subscribedUser = userRepository.findById(user.getId()).orElse(user);
        }
        else {
            this.subscribedUser = user;
        }

        userBinder.setBean(subscribedUser);
        setVisible(true);
    }

}
