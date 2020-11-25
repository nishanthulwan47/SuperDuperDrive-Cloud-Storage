package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.Note;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final AppUserService appUserService;

    public NoteController(NoteService noteService, AppUserService appUserService) {
        this.noteService = noteService;
        this.appUserService = appUserService;
    }

    public ModelAndView postNote(@ModelAttribute Note note, Authentication authentication, Model model) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();

        try {
            Integer noteid = note.getNoteid();
            noteService.createNote(note, userid, noteid);
            model.addAttribute("success", true);

        }
    }
}
