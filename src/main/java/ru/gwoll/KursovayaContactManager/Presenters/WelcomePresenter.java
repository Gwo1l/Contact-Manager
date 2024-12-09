package ru.gwoll.KursovayaContactManager.Presenters;

import com.vaadin.flow.component.UI;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.html.H1;
import com.vaadin.flow.component.html.H2;
import com.vaadin.flow.component.html.Image;
import com.vaadin.flow.component.html.Span;
import com.vaadin.flow.component.icon.VaadinIcon;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.router.BeforeEnterEvent;
import com.vaadin.flow.router.BeforeEnterObserver;
import com.vaadin.flow.router.Route;
import com.vaadin.flow.server.VaadinServletRequest;
import com.vaadin.flow.server.auth.AnonymousAllowed;
import jakarta.annotation.security.PermitAll;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.OAuth2AuthenticatedPrincipal;
import org.springframework.security.oauth2.core.oidc.user.OidcUser;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import ru.gwoll.KursovayaContactManager.Entities.User;
import ru.gwoll.KursovayaContactManager.Services.PagesNavigator;
import ru.gwoll.KursovayaContactManager.Services.UserService;

@Route("")
@PermitAll
public class WelcomePresenter extends VerticalLayout {
    @Autowired
    public WelcomePresenter(UserService userService) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        OAuth2AuthenticatedPrincipal principal = (OAuth2AuthenticatedPrincipal) authentication.getPrincipal();

        String givenName = principal.getAttribute("given_name");
        String email = principal.getAttribute("email");
        String picture = principal.getAttribute("picture");

        H2 header = new H2("Hello " + givenName + " (" + email + ")");
        Button usersButton = new Button("Войти в систему", VaadinIcon.ENTER.create());
        usersButton.addClickListener(e -> PagesNavigator.navigateTo("/u"));
        Image image = new Image(picture, "User Image");

        try {
            User user = userService.findByName(givenName);
        }
        catch (Exception e) {
            User user = new User(givenName);
            userService.saveUser(user);
        }

        SecurityContextHolder.getContext().setAuthentication(authentication);

        setAlignItems(Alignment.CENTER);
        add(header, image, usersButton);
    }

}
