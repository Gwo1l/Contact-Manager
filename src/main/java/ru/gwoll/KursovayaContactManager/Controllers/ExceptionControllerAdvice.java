package ru.gwoll.KursovayaContactManager.Controllers;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ExceptionControllerAdvice {
    @ExceptionHandler(Exception.class)
    public String exception(Exception e, Model model) {
        model.addAttribute("exceptionMessage", e.getMessage());

        return "exception";
    }
}
