package org.project.java.springfotoalbum.service;

import org.project.java.springfotoalbum.model.Photo;
import org.project.java.springfotoalbum.repository.PhotoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PhotoService {
    @Autowired
    PhotoRepository photoRepository;

    public List<Photo> getAllPhotos(){
        return photoRepository.findAll();
    }
    public List<Photo> getFilteredPhotoByTitle(String searchcontent){
        return photoRepository.findByTitleContainingIgnoreCase(searchcontent);
    }
}
