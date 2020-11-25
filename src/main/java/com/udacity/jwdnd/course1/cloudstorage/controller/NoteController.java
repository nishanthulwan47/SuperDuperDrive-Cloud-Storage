package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final AppUserService appUserService;

    public NoteController(NoteService noteService, AppUserService appUserService) {
        this.noteService = noteService;
        this.appUserService = appUserService;
    }

    @PostMapping("/notes")
    public ModelAndView postNote(Authentication authentication, Model model, @ModelAttribute Note note) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();
        Integer noteid = appUser.getUserid();

        try {
            noteService.createNote(note, userid, noteid);
            model.addAttribute("success", true);
            model.addAttribute("message", "New note added !");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Cannot find Note" + e.getMessage());
        }
        return new ModelAndView("result");
    }

    @PostMapping("/notes/delete")
    public ModelAndView deleteNote(Authentication authentication, Model model, @ModelAttribute Note note) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();
        Integer noteid = appUser.getUserid();

        try {
            noteService.deleteNote(note, userid);
            model.addAttribute("success", true);
            model.addAttribute("message", "Note has been deleted successfully");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "Note has not been deleted please try again" + e.getMessage());
        }
        return new ModelAndView("result");
    }
}
