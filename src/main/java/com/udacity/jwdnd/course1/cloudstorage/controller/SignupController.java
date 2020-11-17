package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.validator.PasswordValidator;
import com.udacity.jwdnd.course1.cloudstorage.validator.UsernameValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
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


    public SignupController(AppUserService appUserService, PasswordValidator passwordValidator, UsernameValidator usernameValidator) {
        this.appUserService = appUserService;
        this.passwordValidator = passwordValidator;
        this.usernameValidator = usernameValidator;
    }

    @GetMapping()
    public String signupView() {
        return "signup";
    }

    @PostMapping
    public String signupUser(@ModelAttribute AppUser user, Model model) {
        String signupError = null;

        if (usernameValidator.isCorrect(user.getUsername())) {

            signupError = "1. Username consists of alphanumeric characters (a-z, A-Z, 0-9), lowercase, or uppercase " +
                    "2. Username allowed of the dot (.), underscore(_), and hyphen (-)" +
                    "3. The dot (.), underscore (_), or hyphen (-) must not be the first or last character" +
                    "4. The dot (.), underscore (_), or hyphen (-) does not appear consecutively" +
                    "5. The number of characters must be between 5 to 20";

        }

        if (!appUserService.isUsernameAvailable(user.getUsername())) {
            signupError = "The username already exists.";
        }


        if (passwordValidator.isValid(user.getPassword())) {
            signupError = "1.Password must contain at least one digit [0-9], password must contain at least one " +
                    "lowercase character[a-z], password must contain at least one uppercase character[A-Z], " +
                    "password must contain at least one special character like ! @ # & ( ), password must contain a " +
                    "length of at least 8 characters and a maximum of 20 characters";
        }


        if (signupError == null) {
            int rowsAdded = appUserService.saveAppUser(user);
            if (rowsAdded < 0) {
                signupError = "There was an error signing you up. Please try again.";
            }
        }

        if (signupError == null) {
            model.addAttribute("signupSuccess", true);
        } else {
            model.addAttribute("signupError", signupError);
        }
        return "signup";
    }
}
