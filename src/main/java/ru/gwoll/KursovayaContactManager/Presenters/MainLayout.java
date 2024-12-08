package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.applayout.AppLayout;
import com.vaadin.flow.component.applayout.DrawerToggle;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Header;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.Scroller;
import com.vaadin.flow.component.sidenav.SideNav;
import com.vaadin.flow.component.sidenav.SideNavItem;
import com.vaadin.flow.router.HasDynamicTitle;
import com.vaadin.flow.router.PageTitle;
import com.vaadin.flow.spring.security.AuthenticationContext;
import com.vaadin.flow.theme.lumo.LumoUtility;


/**
 * Основной макет приложения, включающий навигацию, меню и отображение информации о текущем представлении.
 * Содержит верхнюю панель с кнопкой выхода и боковую панель для навигации по разделам.
 */
public class MainLayout extends AppLayout {
    private final AuthenticationContext authenticationContext;
    private H2 viewTitle;


    public MainLayout(AuthenticationContext authenticationContext) {
        this.authenticationContext = authenticationContext;
        setPrimarySection(Section.DRAWER);
        addNavbarContent();
        addDrawerContent();
    }

    private void addNavbarContent() {
        var toggle = new DrawerToggle();
        toggle.setAriaLabel("Menu toggle");
        toggle.setTooltipText("Menu toggle");

        viewTitle = new H2();
        viewTitle.addClassNames(LumoUtility.FontSize.LARGE, LumoUtility.Margin.NONE,
                LumoUtility.Flex.GROW);

        var logout = new Button("Выход", VaadinIcon.EXIT.create(), event -> authenticationContext.logout());

        var header = new Header(toggle, viewTitle, logout);
        header.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.Display.FLEX,
                LumoUtility.Padding.End.MEDIUM, LumoUtility.Width.FULL);

        addToNavbar(false, header);
    }

    private void addDrawerContent() {
        var appName = new Span("Social Library");
        appName.addClassNames(LumoUtility.AlignItems.CENTER, LumoUtility.Display.FLEX,
                LumoUtility.FontSize.LARGE, LumoUtility.FontWeight.SEMIBOLD,
                LumoUtility.Height.XLARGE, LumoUtility.Padding.Horizontal.MEDIUM);

        addToDrawer(appName, new Scroller(createSideNav()));

    }

    private SideNav createSideNav() {
        SideNav nav = new SideNav();

        nav.addItem(new SideNavItem("Пользователи", UsersListPresenter.class,
                VaadinIcon.USER.create()));
        nav.addItem(new SideNavItem("Избранное", SubscriptionsPresenter.class,
                VaadinIcon.THUMBS_UP.create()));

        return nav;
    }

    private String getCurrentPageTitle() {
        if (getContent() == null) {
            return "";
        } else if (getContent() instanceof HasDynamicTitle titleHolder) {
            return titleHolder.getPageTitle();
        } else {
            var title = getContent().getClass().getAnnotation(PageTitle.class);
            return title == null ? "" : title.value();
        }
    }

    @Override
    protected void afterNavigation() {
        super.afterNavigation();
        viewTitle.setText(getCurrentPageTitle());
    }

}
