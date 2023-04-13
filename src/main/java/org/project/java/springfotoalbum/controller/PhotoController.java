package org.project.java.springfotoalbum.controller;

import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @GetMapping
    public String index(Model model, @RequestParam(name = "q") Optional<String> searchcontent){
        List<Photo> photos;
        if (searchcontent.isPresent()){
            photos = photoService.getFilteredPhotoByTitle(searchcontent.get());
            model.addAttribute("searchcontent", searchcontent.get());
        } else {
            photos = photoService.getAllPhotos();
        }
        model.addAttribute("photos", photos);
        return "/photos/index";
    }


}
