package org.project.java.springfotoalbum.service;

import org.project.java.springfotoalbum.exceptions.CategoryNotFoundException;
import org.project.java.springfotoalbum.exceptions.PhotoNotFoundException;
import org.project.java.springfotoalbum.model.Category;
import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.model.PhotoForm;
import org.project.java.springfotoalbum.repository.CategoryRepository;
import org.project.java.springfotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }
    public List<Photo> getFilteredPhotoByTitle(String searchcontent){
        return photoRepository.findByTitleContainingIgnoreCase(searchcontent);
    }

    public List<Photo> getVisiblePhotos(){
        return photoRepository.findByVisibleTrue();
    }

    public List<Photo> getFilteredVisiblePhotos(String searchcontent){
        return photoRepository.findByTitleContainingIgnoreCaseAndVisibleTrue(searchcontent);
    }

    public Photo getPhotoById(Integer id){
        Optional<Photo> photo = photoRepository.findById(id);
        if (photo.isPresent()){
            return photo.get();
        } else {
            throw new PhotoNotFoundException("Can't find photo with this id");
        }
    }
    public Photo addPhoto(PhotoForm formPhoto) throws IOException {
        Photo newPhoto = new Photo();
        newPhoto.setTitle(formPhoto.getTitle());
        newPhoto.setDescription(formPhoto.getDescription());
        newPhoto.setUrl(formPhoto.getMultipartFile().getBytes());
        newPhoto.setVisible(formPhoto.isVisible());
        Set<Category> formCategories = getPhotoCategories(formPhoto);
        newPhoto.setCategories(formCategories);
        return photoRepository.save(newPhoto);

    }

    public Photo updatePhoto(PhotoForm formPhoto, Integer id) throws PhotoNotFoundException, IOException {
        Photo photoToUpdate = getPhotoById(id);
        photoToUpdate.setTitle(formPhoto.getTitle());
        photoToUpdate.setDescription(formPhoto.getDescription());
        photoToUpdate.setUrl(formPhoto.getMultipartFile().getBytes());
        photoToUpdate.setVisible(formPhoto.isVisible());
        photoToUpdate.setCategories(formPhoto.getCategories());
        return photoRepository.save(photoToUpdate);
    }

    public boolean deletePhoto(Integer id) throws PhotoNotFoundException {
        photoRepository.findById(id).orElseThrow(()-> new PhotoNotFoundException(Integer.toString(id)));
        try {
            photoRepository.deleteById(id);
            return true;
        } catch (Exception e){
            return false;
        }
    }
    public Set<Category> getPhotoCategories(PhotoForm formPhoto){
        Set<Category> formCategories = new HashSet<>();
        if (formPhoto.getCategories() != null){
            for (Category cat : formPhoto.getCategories()){
                formCategories.add(categoryRepository.findById(cat.getId()).orElseThrow(()->new CategoryNotFoundException("Category not Found")));
            }
        }
        return formCategories;
    }
}
