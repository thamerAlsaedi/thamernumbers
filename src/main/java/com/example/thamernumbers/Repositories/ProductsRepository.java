package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.Products;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Products, Integer> {

    Products getProductsById(Integer id);
}
