package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.Credential;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.CredentialService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping(value = "/home")
public class HomeController {

    private final AppUserService appUserService;
    private final FileService fileService;
    private final NoteService noteService;
    private final CredentialService credentialService;

    public HomeController(AppUserService appUserService, FileService fileService, NoteService noteService, CredentialService credentialService) {
        this.appUserService = appUserService;
        this.fileService = fileService;
        this.noteService = noteService;
        this.credentialService = credentialService;
    }

   @GetMapping
   public String getHomePage(Authentication authentication, Model model) {
       AppUser appUser =this.appUserService.getUser(authentication.getName());
       Integer userId = appUser.getUserid();
       model.addAttribute("credentials", this.credentialService.getCredentialByUserId(userId));
       model.addAttribute("notes", this.noteService.getAllNotesByUserId(userId));
       model.addAttribute("files", this.fileService.getAllFilesByUserId(userId));
       model.addAttribute("noteForm", new Note());
       model.addAttribute("noteDelete", new Note());
       model.addAttribute("credentialForm", new Credential());
       model.addAttribute("credentialDelete", new Credential());
       model.addAttribute("fileDelete", new File());
       return "home";
   }

    @PostMapping("/logout")
    public String logout() {
        return "login?logout";
    }

    @GetMapping("/logout")
    public String logoutView() {
        return "redirect:/login?logout";
    }

}
