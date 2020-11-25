package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.stereotype.Controller;

@Controller
public class FileController {

    private final FileService fileService;
    private final AppUserService appUserService;

    public FileController(FileService fileService, AppUserService appUserService) {
        this.fileService = fileService;
        this.appUserService = appUserService;
    }


}
