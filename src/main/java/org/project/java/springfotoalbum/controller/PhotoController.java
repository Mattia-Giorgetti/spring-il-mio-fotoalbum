package org.project.java.springfotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.FlashMessage;
import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/{id}")
    public String show(@PathVariable Integer id, Model model){
        try {
            Photo photo = photoService.getPhotoById(id);
            model.addAttribute("photo", photo);
            return "/photos/show";
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping("/create")
    public String create(Model model){
        model.addAttribute("photo", new Photo());
        return "/photos/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model){
        if (bindingResult.hasErrors()){
            return "/photos/create";
        }
        photoService.addPhoto(formPhoto);
        redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "New Photo Created"));
        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try{
            Photo photo = photoService.getPhotoById(id);
            model.addAttribute("photo", photo);
            return "/photos/edit";
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant't find photo with id: " + id);
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") Photo formPhoto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            return "/photos/edit";
        }
        try {
            Photo updatedPhoto = photoService.updatePhoto(formPhoto, id);
            redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "Photo Updated"));
            return "redirect:/photos/" + updatedPhoto.getId();
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/delete/{id}")
    public String deletePhoto(@PathVariable Integer id, RedirectAttributes redirectAttributes){
        try {
            boolean success = photoService.deletePhoto(id);
            if (success){
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "Photo deleted"));
            } else {
                redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Unable to delete this photo"));
            }
        } catch (PhotoNotFoundException e){
            redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.ERROR, "Can't find photo with id " + id));
        }
        return "redirect:/photos";
    }



}