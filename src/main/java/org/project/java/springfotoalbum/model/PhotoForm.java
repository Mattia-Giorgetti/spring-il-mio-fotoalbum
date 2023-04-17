package org.project.java.springfotoalbum.model;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.project.java.springfotoalbum.validators.ValidImageFile;
import org.springframework.web.multipart.MultipartFile;

import java.util.Set;

public class PhotoForm {


    @ValidImageFile(message = "This field is mandatory")
    private MultipartFile multipartFile;
    private Integer id;
    @NotEmpty(message = "This field can't be empty")
    @Size(min = 1, max = 40, message = "Characters between 1 and 40")
    private String title;
    private String description;
    private boolean visible;
    private byte[] url;
    private Set<Category> categories;

    public PhotoForm() {
    }

    public PhotoForm(Photo photo){
        this.id = photo.getId();
        this.title = photo.getTitle();
        this.description = photo.getDescription();
        this.url = photo.getUrl();
        this.visible = photo.isVisible();
        this.categories = photo.getCategories();
    }

    public MultipartFile getMultipartFile() {
        return multipartFile;
    }

    public void setMultipartFile(MultipartFile multipartFile) {
        this.multipartFile = multipartFile;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public byte[] getUrl() {
        return url;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
