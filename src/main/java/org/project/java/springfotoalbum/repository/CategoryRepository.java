package org.project.java.springfotoalbum.repository;

import org.project.java.springfotoalbum.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
