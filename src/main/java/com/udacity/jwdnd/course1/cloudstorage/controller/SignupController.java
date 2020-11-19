package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.validator.PasswordValidator;
import com.udacity.jwdnd.course1.cloudstorage.validator.UsernameValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/signup")
public class SignupController {

    private final AppUserService appUserService;
    private final PasswordValidator passwordValidator;
    private final UsernameValidator usernameValidator;


    public SignupController(AppUserService appUserService, PasswordValidator passwordValidator,
                            UsernameValidator usernameValidator) {
        this.appUserService = appUserService;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute AppUser user, Model model, BindingResult result) {
        String signupError = null;


        if (!appUserService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }

        if (usernameValidator.isCorrect(user.getUsername())) {
            signupError = "Username does not meet minimum requirements";
        }

        if (signupError == null) {
            int rowsAdded = appUserService.saveAppUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (passwordValidator.isValid(user.getPassword())) {

            signupError = "Password does not meet minimum requirements";
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }

        if (result.hasErrors()) {
            return "signup";
        }
        return "signup";

    }
}
