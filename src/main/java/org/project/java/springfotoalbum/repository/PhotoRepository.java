package org.project.java.springfotoalbum.repository;

import org.project.java.springfotoalbum.model.Photo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PhotoRepository extends JpaRepository<Photo, Integer> {
    public List<Photo> findByTitleContainingIgnoreCase(String title);

    public List<Photo> findByVisibleTrue();

    public List<Photo> findByTitleContainingIgnoreCaseAndVisibleTrue(String title);
}
