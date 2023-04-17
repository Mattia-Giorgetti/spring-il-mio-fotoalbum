package org.project.java.springfotoalbum.controller;

import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/files")
public class FileController {
    @Autowired
    private PhotoService photoService;

    @GetMapping("/{imageId}")
    public ResponseEntity<byte[]> serveImage(@PathVariable Integer imageId){
        Photo photo = photoService.getPhotoById(imageId);

        MediaType mediaType = MediaType.IMAGE_JPEG;
        return ResponseEntity.ok().contentType(mediaType).body(photo.getUrl());
    }
}
