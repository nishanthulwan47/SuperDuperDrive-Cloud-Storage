package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    private final AuthenticationService authenticationService;

    private final AppUserService appUserService;

    private final UserMapper userMapper;


    public HomeController(AuthenticationService authenticationService, AppUserService appUserService, UserMapper userMapper) {
        this.authenticationService = authenticationService;
        this.appUserService = appUserService;
        this.userMapper = userMapper;
    }

    public String getHomePage(Authentication authentication, Model model) {

        String username = authentication.getName();

        AppUser appUser = userMapper.getUser(username);
        return "";
    }
}
