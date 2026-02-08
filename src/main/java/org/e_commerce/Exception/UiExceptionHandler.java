package org.e_commerce.Exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice(basePackages = "org.e_commerce.controller")
public class UiExceptionHandler {

    @ExceptionHandler(ItemNotFoundException.class)
    public String handleItemNotFound(ItemNotFoundException ex, Model model) {
        model.addAttribute("errorTitle", "Item Not Found");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorType", "warning");
        return "error";
    }

    @ExceptionHandler(DuplicateItemException.class)
    public String handleDuplicateItem(DuplicateItemException ex, Model model) {
        model.addAttribute("errorTitle", "Duplicate Item");
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorType", "error");
        return "error";
    }

    @ExceptionHandler(Exception.class)
    public String handleGeneric(Exception ex, Model model) {
        model.addAttribute("errorTitle", "Oops!");
        model.addAttribute("errorMessage", "Something went wrong. Please try again.");
        model.addAttribute("errorType", "error");
        return "error";
    }
}
