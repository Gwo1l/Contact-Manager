package ru.gwoll.KursovayaContactManager.Configs;

import com.vaadin.flow.spring.security.VaadinWebSecurity;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import ru.gwoll.KursovayaContactManager.Presenters.LoginPresenter;


@EnableWebSecurity
@EnableMethodSecurity(jsr250Enabled = true)
@Configuration
public class SecurityConfig extends VaadinWebSecurity {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        super.configure(http);
        setLoginView(http, LoginPresenter.class);

        /*http.oauth2Login(oauth2Login ->
                oauth2Login
                        .loginPage("/login")
                        .successHandler(successHandler())
        );*/
        http.oauth2Login(oauth2Login ->
                oauth2Login
                        .loginPage("/login").permitAll());
    }

    /*@Bean
    public SavedRequestAwareAuthenticationSuccessHandler successHandler() {
        SavedRequestAwareAuthenticationSuccessHandler successHandler = new SavedRequestAwareAuthenticationSuccessHandler();
        successHandler.setDefaultTargetUrl("/welcome");
        return successHandler;
    }*/

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
