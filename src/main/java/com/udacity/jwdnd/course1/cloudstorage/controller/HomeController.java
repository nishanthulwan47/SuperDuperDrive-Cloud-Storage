package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.mapper.UserMapper;
import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/home")
public class HomeController {


    private final AppUserService appUserService;
    private final CredentialService credentialService;
    private final NoteService noteService;
    private final FileService fileService;


    public HomeController(AuthenticationService authenticationService, AppUserService appUserService,
                          UserMapper userMapper, CredentialService credentialService, NoteService noteService,
                          FileService fileService) {

        this.appUserService = appUserService;
        this.credentialService = credentialService;
        this.noteService = noteService;
        this.fileService = fileService;
    }

    @GetMapping
    public String HomePageView() {
        return "home";
    }

    @GetMapping("/home")
    public String ListUploadFiles(Authentication authentication, Model model) throws IOException {

        AppUser appUser = appUserService.getUser(authentication.getPrincipal().toString());
        Integer userId = appUser.getUserid();
        model.addAttribute("files", this.fileService.getAllFilesByUserId(userId));
        model.addAttribute("credentials", this.credentialService.getCredentialByUserId(userId));
        model.addAttribute("notes", this.noteService.getAllNotesByUserId(userId));
        model.addAttribute("newNote", new Note());
        //model.addAttribute("newCredential", new Credential());

        model.addAttribute("credentialService", credentialService);
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

    public String uploadFile(@RequestParam("fileUpload") Authentication auth, Model model,
                             RedirectAttributes redirectAttributes,
                             MultipartFile fileUpload) {

        AppUser appUser = appUserService.getUser(auth.getPrincipal().toString());
        model.addAttribute("activeTab", "files");
        model.addAttribute("errorMessage", false);
        model.addAttribute("successMessage", false);

        if (fileUpload.getOriginalFilename().isEmpty()) {
            model.addAttribute("errorMessage", "Please select file to upload");
            return "result";
        }
        if (!fileService.isFilenameAvailable(fileUpload.getOriginalFilename(), appUser.getUserid())) {
            model.addAttribute("errorMessage", "File with same filename already exists");
            return "result";
        }

        try {
            fileService.uploadFile(new File(fileUpload.getOriginalFilename(), fileUpload.getContentType(),
                    fileUpload.getBytes(), fileUpload.getSize(), appUser.getUserid(), + ""));
            List<File> files = fileService.getAllFilesByUserId(appUser.getUserid());
            model.addAttribute("files", files);
            model.addAttribute("successMessage", files);


        } catch (IOException e) {
            e.printStackTrace();
            model.addAttribute("errorMessage", e.getMessage());
        }

        return "result";

    }



}
