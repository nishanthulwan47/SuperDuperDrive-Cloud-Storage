package com.udacity.jwdnd.course1.cloudstorage.controller;

import com.udacity.jwdnd.course1.cloudstorage.model.AppUser;
import com.udacity.jwdnd.course1.cloudstorage.model.File;
import com.udacity.jwdnd.course1.cloudstorage.services.AppUserService;
import com.udacity.jwdnd.course1.cloudstorage.services.FileService;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FileController {

    private final FileService fileService;
    private final AppUserService appUserService;

    public FileController(FileService fileService, AppUserService appUserService) {
        this.fileService = fileService;
        this.appUserService = appUserService;
    }

    @PostMapping("/file-upload")
    public ModelAndView postFile(@RequestParam("fileUpload") MultipartFile multipartFile, Authentication authentication,
                                 Model model) {
        if (multipartFile.isEmpty()) {
            model.addAttribute("error", true);
            model.addAttribute("message", "File not selected to upload");
            return new ModelAndView("result");
        }

        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userId = appUser.getUserid();

        if (fileService.isFilenameAvailable(multipartFile.getOriginalFilename(), userId)) {
            model.addAttribute("error", true);
            model.addAttribute("message", "file name already exists");
            return new ModelAndView("result");
        }
        try {
            fileService.createFile(multipartFile, userId);
            model.addAttribute("success", true);
            model.addAttribute("message", "New File added successfully");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "System error!" + e.getMessage());
        }
        return new ModelAndView("result");
    }

    @PostMapping("/credentials/delete")
    public ModelAndView deleteNote(@ModelAttribute File fileDelete, Authentication authentication, Model model) {
        AppUser appUser = this.appUserService.getUser(authentication.getName());
        Integer userid = appUser.getUserid();

        try {
            fileService.deleteFile(fileDelete, userid);
            model.addAttribute("success", true);
            model.addAttribute("message", "file Deleted");
        } catch (Exception e) {
            model.addAttribute("error", true);
            model.addAttribute("message", "System error!" + e.getMessage());
        }
        return new ModelAndView("result");
    }

    public ResponseEntity<Resource> donwload(@PathVariable("fileId") Integer fileId) {
        File file = fileService.getFileById(fileId);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add(httpHeaders.CONTENT_DISPOSITION, "attachment; filename = " + file.getFilename());
        httpHeaders.add("Cache-control", "no-cache, no-store, must-revalidate");
        httpHeaders.add("Pragma", "no-cache");
        httpHeaders.add("Expires", "0");
        ByteArrayResource resource = new ByteArrayResource(file.getFiledata());
        return ResponseEntity.ok()
                .headers(httpHeaders)
                .body(resource);

    }
}
