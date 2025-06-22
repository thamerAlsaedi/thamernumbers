package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.Models.Products;
import com.example.thamernumbers.Repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServices {

    private final ProductsRepository productsRepository;

    //  this method ==>  gets all products record from Repository ==> table
    public List<Products> getAllProducts() {
        List<Products> products = productsRepository.findAll();
       return products;
    }

    // in this method we will get product By ID
    public Products getProductById(Integer id) {
        Products product = productsRepository.getProductsById(id);
        return product;
    }

    // in this method we will update Product data by id
    public void updateProduct(Products product) {
        Products existingProduct = productsRepository.findById(product.getId())
                .orElseThrow(() -> new ApiException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setBean(product.getBean());
        existingProduct.setCafe(product.getCafe());

        productsRepository.save(existingProduct);
    }


    // in this method we will delete product by id
    public void deleteProduct(Integer id) {
         Products product = productsRepository.getProductsById(id);
         if(product == null){
             throw new ApiException("Product not found");
         }
         productsRepository.delete(product);
    }


}
