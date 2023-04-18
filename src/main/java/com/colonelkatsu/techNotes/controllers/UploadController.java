package com.colonelkatsu.techNotes.controllers;

import javax.annotation.Resource;

import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

public class UploadController {
    
    @GetMapping("/upload/new")
    @PreAuthorize("isAuthenticated()")
    public String newUpload(Model model){
        return "upload/new";
    }

    @PostMapping("/upload/new")
    @PreAuthorize("isAuthenticated()")
    public String submitNewUpload(Model model, @RequestParam("file") MultipartFile file){
        // TODO : Additional logic here!
        return "upload/new";
    }

    @GetMapping("/upload/image")
    @PreAuthorize("isAuthenticated()")
    public String getAllImages(Model model) {
        // TODO : Additional logic here!
        return "upload/read";
    }

    @GetMapping("/upload/image/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename){
        // TODO : Additional logic here!
        return null;
    }
}
