package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.AuthenticationService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.IOException;

@Controller
@RequestMapping("/home")
public class HomeController {


    private final AppUserService appUserService;


    private final FileService fileService;


    public HomeController(AuthenticationService authenticationService, AppUserService appUserService, UserMapper userMapper, FileService fileService) {

        this.appUserService = appUserService;

        this.fileService = fileService;
    }

    @GetMapping("/home")
    public String getHomePage(Authentication authentication, Model model) throws IOException {

        AppUser appUser = appUserService.getUser(authentication.getPrincipal().toString());
        Integer userId = appUser.getUserid();
        model.addAttribute("files", this.fileService.getAllFilesByUserId(userId));
        return "home";
    }

}
