package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.stereotype.Controller;

@Controller
public class CredentialController {

    private final AppUserService appUserService;
    private final CredentialService credentialService;

    public CredentialController(AppUserService appUserService, CredentialService credentialService) {
        this.appUserService = appUserService;
        this.credentialService = credentialService;
    }

}
