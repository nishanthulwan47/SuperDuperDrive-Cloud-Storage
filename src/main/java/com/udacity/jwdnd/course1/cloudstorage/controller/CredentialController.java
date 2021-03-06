package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class CredentialController {

    private final AppUserService appUserService;
    private final CredentialService credentialService;

    public CredentialController(AppUserService appUserService, CredentialService credentialService) {
        this.appUserService = appUserService;
        this.credentialService = credentialService;
    }

    @PostMapping("/credentials")
    public ModelAndView postCredential(Authentication authentication, Model model, @ModelAttribute Credential credential) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();
        credential.setUserid(userid);

        try {
            if (credential.getCredentialid() == null) {
                credentialService.addCredential(credential);
            } else {
                System.out.println("HERE CRED");
                credentialService.editCredential(credential);
            }
            model.addAttribute("success", true);
            model.addAttribute("message", "New credential added!");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "System error!" + e.getMessage());
        }
        return new ModelAndView("result");
    }

    @PostMapping("/delete/credential")
    public ModelAndView deleteCredential(Authentication authentication, Model model, @ModelAttribute Credential credentialDelete) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();
        Integer credentialId = credentialDelete.getCredentialid();


        try {
            credentialService.deleteCredential(credentialDelete, credentialId);
            model.addAttribute("success", true);
            model.addAttribute("message", "Credentials deleted!");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "System error!" + e.getMessage());
        }
        return new ModelAndView("result");
    }
}
