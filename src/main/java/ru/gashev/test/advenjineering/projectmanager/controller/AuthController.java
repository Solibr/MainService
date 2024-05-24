package ru.gashev.test.advenjineering.projectmanager.controller;

import jakarta.websocket.server.PathParam;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import ru.gashev.test.advenjineering.projectmanager.dto.UserRegistrationData;
import ru.gashev.test.advenjineering.projectmanager.exception.PasswordsNotMatchException;
import ru.gashev.test.advenjineering.projectmanager.exception.UsernameAlreadyExistsException;
import ru.gashev.test.advenjineering.projectmanager.service.UserService;

@Controller
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String getLoginPage(@PathParam("error") boolean error, Model model) {
        if (error)
            model.addAttribute("error", error);
        return "login";
    }

    @GetMapping("/register")
    public String getRegistrationPage(@ModelAttribute("user") UserRegistrationData userRegistrationData) {
        return "register";
    }

    @PostMapping("/register")
    public String register(@ModelAttribute("user") UserRegistrationData userRegistrationData) {
        userService.registerNewUser(userRegistrationData);
        return "redirect:/";
    }

    @ExceptionHandler({UsernameAlreadyExistsException.class, PasswordsNotMatchException.class})
    public ModelAndView userAlreadyExists(Exception e) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("register");
        modelAndView.addObject("registerError", true);
        modelAndView.addObject("registerErrorMessage", e.getMessage());
        modelAndView.addObject("user", new UserRegistrationData());
        return modelAndView;
    }


}
