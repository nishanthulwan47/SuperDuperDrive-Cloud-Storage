package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.NoteService;
import org.springframework.stereotype.Controller;

@Controller
public class NoteController {
    private final NoteService noteService;
    private final AppUserService appUserService;

    public NoteController(NoteService noteService, AppUserService appUserService) {
        this.noteService = noteService;
        this.appUserService = appUserService;
    }

}
