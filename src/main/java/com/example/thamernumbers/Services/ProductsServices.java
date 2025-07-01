package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.Models.Product;
import com.example.thamernumbers.Repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductsServices {

    private final ProductsRepository productsRepository;

    //  this method ==>  gets all products record from Repository ==> table
    public List<Product> getAllProducts() {
        List<Product> products;
        products = productsRepository.findAll();
        if (products.isEmpty()) {
            throw new ApiException("No products found");
        }
        return products;
    }

    // in this method we will get product By ID
    public Product getProductById(Integer product_Id) {
        Product product;
        product = productsRepository.getProductsById(product_Id);
        if (product == null) {
            throw new ApiException("Product not found with id " + product_Id);
        }
        return product;
    }

    // in this method we will update Product data by id
    public void updateProduct(Product product) {
        Product existingProduct = productsRepository.findById(product.getId())
                .orElseThrow(() -> new ApiException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCoffeeBean(product.getCoffeeBean());
        existingProduct.setCafe(product.getCafe());

        productsRepository.save(existingProduct);
    }


    // in this method we will delete product by id
    public void deleteProduct(Integer id) {
         Product product = productsRepository.getProductsById(id);
         if(product == null){
             throw new ApiException("Product not found");
         }
         productsRepository.delete(product);
    }


}
