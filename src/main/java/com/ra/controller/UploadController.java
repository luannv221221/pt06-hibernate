package com.ra.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.StandardOpenOption;

@PropertySource("classpath:application.properties")
@Controller
public class UploadController {
    @Value("${path}")
    private String path;
    @Autowired
    private HttpServletRequest httpServletRequest;
    @GetMapping("/upload")
    public String uploadFile(){
        return "upload";
    }
    @PostMapping("/upload")
    public String postUpload(@RequestParam("fileUpload") MultipartFile file){
        String fileName = file.getOriginalFilename();
//        String path = httpServletRequest.getServletContext().getRealPath("uploads");
//        File f = new File(path);
        File destination = new File(path+"/"+fileName);
        try {
//                file.transferTo(destination);
            Files.write(destination.toPath(),file.getBytes(), StandardOpenOption.CREATE);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println(path);
        return "redirect:/upload";
    }
}
