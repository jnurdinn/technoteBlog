package com.colonelkatsu.techNotes.services;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.FileAlreadyExistsException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.FileSystemUtils;
import org.springframework.web.multipart.MultipartFile;

@Service
public class UploadService {
    private final Path root = Paths.get("./uploads");

    public void init(){
        try {
            Files.createDirectories(root);
        } catch (IOException e){
            throw new RuntimeException("Unable to initialize folder for upload!");
        }
    }

    public void save(MultipartFile file){
        try {
            Files.copy(file.getInputStream(), this.root.resolve(file.getOriginalFilename()));
        } catch (Exception e) {
            if (e instanceof FileAlreadyExistsException){
                throw new RuntimeException("This file name already exists.");
            }
        }
    }

    public Resource load(String filename){
        try {
            Path file = root.resolve(filename);
            Resource resource = new UrlResource(file.toUri());
    
            if(resource.exists() || resource.isReadable()){
                return resource;
            }

            throw new RuntimeException("Unable to read file!");
    
        } catch (MalformedURLException e) {
            throw new RuntimeException("Error: " + e.getMessage());
        }
    }

    public void deleteAll(){
        FileSystemUtils.deleteRecursively(root.toFile());
    }

    public boolean delete(String filename) {
        try {
          Path file = root.resolve(filename);
          return Files.deleteIfExists(file);
        } catch (IOException e) {
          throw new RuntimeException("Error: " + e.getMessage());
        }
      }    


    public Stream<Path> loadAll(){
        try {
            return Files.walk(this.root, 1).filter(path -> !path.equals(this.root)).map(this.root::relativize);
        } catch (IOException e) {
            throw new RuntimeException("Unable to load the files!");
        }
    }
}
