package org.project.java.springfotoalbum.controller;

import jakarta.validation.Valid;
import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.FlashMessage;
import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.model.PhotoForm;
import org.project.java.springfotoalbum.service.CategoryService;
import org.project.java.springfotoalbum.service.PhotoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/photos")
public class PhotoController {
    @Autowired
    PhotoService photoService;

    @Autowired
    CategoryService categoryService;

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
        PhotoForm photo = new PhotoForm(new Photo());
        model.addAttribute("photo", photo);
        model.addAttribute("categoryList", categoryService.getAllCategories());
        return "/photos/create";
    }

    @PostMapping("/create")
    public String store(@Valid @ModelAttribute("photo") PhotoForm photoForm, BindingResult bindingResult, RedirectAttributes redirectAttributes, Model model) throws IOException {
        if (bindingResult.hasErrors()){
            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "/photos/create";
        }
        photoService.addPhoto(photoForm);
        redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "New Photo Created"));
        return "redirect:/photos";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable Integer id, Model model){
        try{
            Photo photoToUpdate = photoService.getPhotoById(id);
            PhotoForm photoForm = new PhotoForm(photoToUpdate);
            model.addAttribute("photo", photoForm);
            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "/photos/edit";
        } catch (PhotoNotFoundException e){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Cant't find photo with id: " + id);
        }
    }

    @PostMapping("/edit/{id}")
    public String update(@PathVariable Integer id, @Valid @ModelAttribute("photo") PhotoForm formPhoto, BindingResult bindingResult, Model model, RedirectAttributes redirectAttributes){
        if (bindingResult.hasErrors()){
            model.addAttribute("categoryList", categoryService.getAllCategories());
            return "/photos/edit";
        }
        try {
            Photo updatedPhoto = photoService.updatePhoto(formPhoto, id);
            redirectAttributes.addFlashAttribute("message", new FlashMessage(FlashMessage.FleshMessageType.SUCCESS, "Photo Updated"));
            return "redirect:/photos/" + updatedPhoto.getId();
        } catch (PhotoNotFoundException | IOException e){
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
