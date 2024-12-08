package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.SubscriptionsRepository;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.Subscription;
import ru.gwoll.KursovayaContactManager.Entities.User;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class UserEditorService {
    private CurrentUserService currentUserService;
    private SubscriptionsRepository subscriptionsRepository;
    private UserRepository userRepository;
    private User me;
    private Grid<User> usersGrid;

    @Autowired
    public UserEditorService(CurrentUserService currentUserService,
                             SubscriptionsRepository subscriptionsRepository,
                             UserRepository userRepository) {
        this.userRepository = userRepository;
        this.currentUserService = currentUserService;
        this.subscriptionsRepository = subscriptionsRepository;
        createAndSetUsersGrid();
    }

    private void createAndSetUsersGrid() {
        usersGrid = new Grid<>(User.class);
        usersGrid.setHeight("700px");
        usersGrid.setWidth("900px");
        usersGrid.setColumns();

        usersGrid.addColumn(User::getName).setHeader("Имя").setWidth("80%").setTextAlign(ColumnTextAlign.CENTER);
        usersGrid.addColumn(User::getGender).setHeader("Пол").setWidth("20%").setTextAlign(ColumnTextAlign.CENTER);

        usersGrid.getElement().getStyle().set("margin-left", "auto");
        usersGrid.getElement().getStyle().set("margin-right", "auto");
    }

    public Grid<User> getUsersGrid() {
        return usersGrid;
    }

    /**
     * Получает текущего пользователя. Если текущий пользователь не был установлен ранее, он загружается через сервис.
     *
     * @return текущий пользователь
     */
    private User getUser() {
        if (me == null) {
            me = currentUserService.getCurrentUser();
        }
        return me;
    }

    /**
     * Подписывает текущего пользователя на указанного пользователя.
     * Если подписка уже существует, возвращается {@code false}.
     *
     * @param user пользователь, на которого нужно подписаться
     * @return {@code true}, если подписка была успешно добавлена, иначе {@code false}
     */
    public boolean subscribe(User user) {
        me = getUser();
        Subscription findingSubscription = subscriptionsRepository.findSubscriptionByUserAndSubscribedUser(me, user);

        if (findingSubscription == null) {
            Subscription subscription = new Subscription(me, user);
            subscriptionsRepository.save(subscription);
            return true;
        }
        else {
            return false;
        }
    }


}
