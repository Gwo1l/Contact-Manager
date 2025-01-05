package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.data.value.ValueChangeMode;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.router.Route;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import ru.gwoll.KursovayaContactManager.CRUDRepositories.UserRepository;
import ru.gwoll.KursovayaContactManager.Entities.User;
import ru.gwoll.KursovayaContactManager.Services.PagesNavigator;
import ru.gwoll.KursovayaContactManager.Services.UserEditorService;
import ru.gwoll.KursovayaContactManager.Services.UsersFilterService;
import ru.gwoll.KursovayaContactManager.SubPresenters.UserEditorPresenter;

import java.util.Collection;


@Route(value = "u", layout = MainLayout.class)
@PageTitle("Пользователи")
@PermitAll
public class UsersListPresenter extends VerticalLayout {
    private final String SUBSCRIPTIONS_PAGE = "subscriptions";
    private final TextField filter = new TextField("", "Нажмите на фильтр");
    private final Button subscriptionsButton = new Button("Перейти в изрбанное", VaadinIcon.USER.create());
    private HorizontalLayout toolbar;
    private TextField performanceField;
    private Grid<User> usersGrid;

    @Autowired
    public UsersListPresenter(UserRepository userRepository,
                              UserEditorPresenter userEditorPresenter,
                              UserEditorService userEditorService,
                              UsersFilterService usersFilterService) {
        toolbar = new HorizontalLayout();
        usersGrid = userEditorService.getUsersGrid();
        usersFilterService.setGrid(usersGrid);
        performanceField = new TextField("Время");
        performanceField.setReadOnly(true);

        toolbar.add(filter, subscriptionsButton, performanceField);

        add(toolbar, usersGrid, userEditorPresenter);

        filter.setValueChangeMode(ValueChangeMode.EAGER);
        filter.addValueChangeListener(e -> {
            long time = usersFilterService.showUsers(e.getValue());
            performanceField.setValue(time + " мс");
        });


        usersGrid.asSingleSelect().addValueChangeListener(e -> {
            userEditorPresenter.setEditingUser(e.getValue());
        });


        subscriptionsButton.addClickListener(e -> PagesNavigator.navigateTo(SUBSCRIPTIONS_PAGE));

        usersGrid.setItems((Collection<User>) userRepository.findAll());
    }

}
