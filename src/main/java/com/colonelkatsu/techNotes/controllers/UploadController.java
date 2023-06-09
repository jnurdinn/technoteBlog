package com.colonelkatsu.techNotes.controllers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.core.io.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.colonelkatsu.techNotes.models.ImageInfo;
import com.colonelkatsu.techNotes.services.UploadService;

@Controller
public class UploadController {

    @Autowired
    UploadService uploadService;

    @GetMapping("/uploads/new")
    @PreAuthorize("isAuthenticated()")
    public String newUpload(Model model) {
        return "upload/new";
    }

    @PostMapping("/uploads/new")
    @PreAuthorize("isAuthenticated()")
    public String submitNewUpload(Model model, @RequestParam("file") MultipartFile file, RedirectAttributes redirectAttributes) {
        String message = "";

        try {
            uploadService.save(file);
            message = "Image uploaded successfully: " + file.getOriginalFilename();
            redirectAttributes.addFlashAttribute("message", message);
            return "redirect:/uploads";

        } catch (Exception e) {
            message = "Unable to upload image: " + file.getOriginalFilename() + ". Error: " + e.getMessage();
            return "upload/new";
        }
    }

    @GetMapping("/uploads")
    @PreAuthorize("isAuthenticated()")
    public String getAllImages(Model model) {
        List<ImageInfo> images = uploadService.loadAll().map(path -> {
            String filename = path.getFileName().toString();
            String url = MvcUriComponentsBuilder
                    .fromMethodName(UploadController.class, "getImage", path.getFileName().toString()).build()
                    .toString();

            return new ImageInfo(Long.valueOf(0), filename, url);
        }).collect(Collectors.toList());

        if (images.size() > 0){
          for(int i=0; i < images.size(); i++){
            images.get(i).setId(Long.valueOf(i));
          }
        }
        
        model.addAttribute("images", images);

        return "upload/read";
    }

    @GetMapping("/uploads/read/{filename:.+}")
    public ResponseEntity<Resource> getImage(@PathVariable String filename) {
        Resource file = uploadService.load(filename);
        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"")
                .body(file);
    }

    @GetMapping("/uploads/delete/{filename:.+}")
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public String deleteFile(@PathVariable String filename, Model model, RedirectAttributes redirectAttributes) {
        try {
          boolean existed = uploadService.delete(filename);
    
          if (existed) {
            redirectAttributes.addFlashAttribute("message", "File deleted successfully: " + filename);
          } else {
            redirectAttributes.addFlashAttribute("message", "The file does not exist!");
          }
        } catch (Exception e) {
          redirectAttributes.addFlashAttribute("message",
              "Unable to delete file: " + filename + ". Error: " + e.getMessage());
        }
        return "redirect:/uploads";
      }
}
