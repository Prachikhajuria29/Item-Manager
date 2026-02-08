package org.e_commerce.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UiExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFound(ItemNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());
        return "error";   // error.html
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("message", "Something went wrong!");
        return "error";
    }
}
