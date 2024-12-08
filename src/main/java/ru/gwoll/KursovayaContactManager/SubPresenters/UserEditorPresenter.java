package ru.gwoll.KursovayaContactManager.SubPresenters;

import com.vaadin.flow.component.KeyNotifier;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.notification.NotificationVariant;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.binder.Binder;
import com.vaadin.flow.spring.annotation.SpringComponent;
import com.vaadin.flow.spring.annotation.UIScope;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.LumoUtility;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;
import ru.gwoll.KursovayaContactManager.Services.CustomNotification;
import ru.gwoll.KursovayaContactManager.Services.UserEditorService;


@SpringComponent
@UIScope
public class UserEditorPresenter extends VerticalLayout implements KeyNotifier {
    private final UserRepository userRepository;
    private User editingUser;
    private TextField name = new TextField("Имя");
    private Button subscribeButton = new Button("Добавить в избранное", VaadinIcon.PLUS.create());
    private TextField number = new TextField("Номер телефона");
    private TextField gender = new TextField("Пол");
    private Binder<User> binder = new Binder<>(User.class);

    @Autowired
    public UserEditorPresenter(UserRepository userRepository,
                               UserEditorService userEditorService) {
        this.userRepository = userRepository;

        name.setReadOnly(true);
        number.setReadOnly(true);
        gender.setReadOnly(true);

        setHorizontalComponentAlignment(Alignment.CENTER, name, number, gender, subscribeButton);

        add(name, number, gender, subscribeButton);

        binder.bindInstanceFields(this);

        setSpacing(true);

        subscribeButton.addClickListener(e -> {
            if (userEditorService.subscribe(editingUser)) {
                CustomNotification.showNotification("✓", NotificationVariant.LUMO_SUCCESS);
            }
            else {
                CustomNotification.showNotification("Вы уже подписаны на этого пользователя",
                        NotificationVariant.LUMO_WARNING);
            }
        });
        setVisible(false);
    }


    /**
     * Устанавливает редактируемого пользователя и отображает его информацию.
     *
     * @param newUser Новый пользователь для редактирования
     */
    public void setEditingUser(User newUser) {
        if (newUser == null) {
            setVisible(true);
            return;
        }

        if (newUser.getId() != null) {
            this.editingUser = userRepository.findById(newUser.getId()).orElse(newUser);
        }
        else {
            this.editingUser = newUser;
        }

        number.setValue(editingUser.getPhoneNumber() == null ? "" : editingUser.getPhoneNumber());

        binder.setBean(editingUser);
        setVisible(true);
    }



}
