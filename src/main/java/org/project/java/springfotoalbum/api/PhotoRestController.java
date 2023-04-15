package org.project.java.springfotoalbum.api;

import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/api/photos")
public class PhotoRestController {

    @Autowired
    PhotoService photoService;

    @GetMapping
    public List<Photo> photoList(@RequestParam(name = "q")Optional<String> queryString){
        if (queryString.isPresent()){
            return photoService.getFilteredVisiblePhotos(queryString.get());
        } else {
            return photoService.getVisiblePhotos();
        }
    }

    @GetMapping("/{id}")
    public Photo photoById(@PathVariable Integer id){
        try {
            return photoService.getPhotoById(id);
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
