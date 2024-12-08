package ru.gwoll.KursovayaContactManager.Services;

import com.vaadin.flow.component.grid.ColumnTextAlign;
import com.vaadin.flow.component.grid.Grid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.SubscriptionsRepository;
import ru.gwoll.KursovayaContactManager.Entities.Subscription;
import ru.gwoll.KursovayaContactManager.Entities.User;

import java.util.List;


@Service
@Scope(value = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SubscriptionsService {
    private SubscriptionsRepository subscriptionsRepository;

    private User me;
    private Grid<User> subscriptionsGrid;


    private CurrentUserService currentUserService;

    @Autowired
    public SubscriptionsService(SubscriptionsRepository subscriptionsRepository,
                                CurrentUserService currentUserService) {
        this.currentUserService = currentUserService;
        this.subscriptionsRepository = subscriptionsRepository;
        createAndSetSubsciptionsGrid();
    }


    public void unsubscribe(User subscribedUser) {
        me = getUser();
        Subscription subscription = subscriptionsRepository.findSubscriptionByUserAndSubscribedUser(me, subscribedUser);

        if (subscription == null) {
            throw new IllegalArgumentException("Подписка не найдена");
        }

        subscriptionsRepository.delete(subscription);
        subscriptionsGrid.setItems(subscriptionsRepository.findAllSubscribedUsers());
    }

    private void createAndSetSubsciptionsGrid() {
        subscriptionsGrid = new Grid<>(User.class);
        subscriptionsGrid.setHeight("300px");
        subscriptionsGrid.setWidth("900px");
        subscriptionsGrid.setColumns();

        subscriptionsGrid.addColumn(User::getName).setHeader("Имя").setWidth("540px")
                .setTextAlign(ColumnTextAlign.CENTER);;
        subscriptionsGrid.addColumn(User::getGender).setHeader("Пол").setWidth("90px")
                .setTextAlign(ColumnTextAlign.CENTER);;
    }



    public Grid<User> getSubscriptionsGrid() {
        return subscriptionsGrid;
    }

    private User getUser() {
        if (me == null) {
            me = currentUserService.getCurrentUser();
        }
        return me;
    }

    public List<User> getSubscribedUsers() {
        List<Subscription> subscriptions = subscriptionsRepository
                .findSubscriptionsByUser(currentUserService.getCurrentUser());

        return subscriptions.stream()
                .map(Subscription::getSubscribedUser)
                .toList();
    }


}
