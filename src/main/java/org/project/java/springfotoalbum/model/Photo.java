package org.project.java.springfotoalbum.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.boot.context.properties.bind.DefaultValue;

import java.util.Set;

@Entity
@Table(name = "photos")
public class Photo {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "This field can't be empty")
    @Size(min = 1, max = 40, message = "Characters between 1 and 40")
    private String title;
    @Lob
    private String description;

    @NotEmpty(message = "This field can't be empty")
    private String url;

    private boolean visible;

    @ManyToMany
    @JoinTable(name = "photos_categories", joinColumns = @JoinColumn(name = "photo_id"), inverseJoinColumns = @JoinColumn(name = "category_id"))
    private Set<Category> categories;

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

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public boolean isVisible() {
        return visible;
    }

    public void setVisible(boolean visible) {
        this.visible = visible;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }
}
