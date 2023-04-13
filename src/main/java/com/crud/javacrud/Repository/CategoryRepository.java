package com.crud.javacrud.Repository;

import com.crud.javacrud.Model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {

}
