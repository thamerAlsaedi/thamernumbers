package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.Models.Product;
import com.example.thamernumbers.Services.ProductsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsServices productsServices;

    @GetMapping("/get-all")
    public List<Product> getAllProducts(){
        List<Product> products = productsServices.getAllProducts();
        return products;
    }

    @GetMapping("/get-by-id/{id}")
    public Product getProductById(@PathVariable  Integer id){
        Product product = productsServices.getProductById(id);
        return product;
    }
}
