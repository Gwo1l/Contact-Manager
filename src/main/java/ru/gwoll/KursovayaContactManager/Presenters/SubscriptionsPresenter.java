package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;
import ru.gwoll.KursovayaContactManager.Services.SubscriptionsService;
import ru.gwoll.KursovayaContactManager.SubPresenters.SubscriptionUserEditorPresenter;


@Route(value = "subscriptions", layout = MainLayout.class)
@PageTitle("Подписки")
@PermitAll
public class SubscriptionsPresenter extends VerticalLayout {
    private Grid<User> subscriptionsGrid;
    private H1 subscriptionsTitle;
    @Autowired
    public SubscriptionsPresenter(
            SubscriptionsService subscriptionsService,
            SubscriptionUserEditorPresenter subscriptionUserEditorPresenter)
    {
        this.subscriptionsGrid = subscriptionsService.getSubscriptionsGrid();
        this.subscriptionsTitle = new H1("Избранное");

        setHorizontalComponentAlignment(Alignment.CENTER, subscriptionsTitle, subscriptionsGrid);

        subscriptionsGrid.asSingleSelect().addValueChangeListener(e ->
                subscriptionUserEditorPresenter.editSubscription(e.getValue()));

        add(subscriptionsTitle, subscriptionsGrid, subscriptionUserEditorPresenter);

        subscriptionsGrid.setItems(subscriptionsService.getSubscribedUsers());

    }
}
