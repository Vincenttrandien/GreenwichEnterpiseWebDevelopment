package com.greenwich.backend.controller;

import com.greenwich.backend.entity.avatar.Photo;
import com.greenwich.backend.response.MessageResponse;
import com.greenwich.backend.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/photo")
public class PhotoController {

    @Autowired
    PhotoService photoService;

    @PostMapping("/add")
    public ResponseEntity<?> addPhoto(@RequestParam("title") String title,
                                      @RequestParam("image") MultipartFile image, Model model)
            throws IOException {
        photoService.addPhoto(title, image);
        return ResponseEntity.ok(new MessageResponse("Upload Avatar successfully!"));
    }

//    @DeleteMapping("{/id}")
//    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws IOException {
//        return ResponseEntity.status(HttpStatus.OK).body(photoService.deletePhoto(photoService.getPhoto()));
//    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getPhoto(@PathVariable String id, Model model) {
        Photo photo = photoService.getPhoto(id);
        model.addAttribute("title", photo.getTitle());
        model.addAttribute("image",
                Base64.getEncoder().encodeToString(photo.getImage().getData()));

        return ResponseEntity.status(HttpStatus.OK).body(photoService.getPhoto(id));
    }
}
