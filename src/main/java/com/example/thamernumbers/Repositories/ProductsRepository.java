package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

    Product getProductsById(Integer id);
}
