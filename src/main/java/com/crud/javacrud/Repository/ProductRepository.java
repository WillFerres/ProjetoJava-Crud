package com.crud.javacrud.Repository;

import com.crud.javacrud.Model.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product,Long> {

}
