package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.Models.Products;
import com.example.thamernumbers.Services.ProductsServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsServices productsServices;

    @GetMapping("/get")
    public List<Products> getAllProducts(){
        List<Products> products = productsServices.getAllProducts();
        return products;
    }

    @GetMapping("/get-by-id/{id}")
    public Products getProductById(@PathVariable  Integer id){
        Products product = productsServices.getProductById(id);
        return product;
    }


}
