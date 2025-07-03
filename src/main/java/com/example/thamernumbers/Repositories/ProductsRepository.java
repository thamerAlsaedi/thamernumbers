package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.Product;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ProductsRepository extends JpaRepository<Product, Integer> {

    Product getProductsById(Integer id);


     Optional<Product> findByNameAndCafeId(String name, Integer cafeId);

}
