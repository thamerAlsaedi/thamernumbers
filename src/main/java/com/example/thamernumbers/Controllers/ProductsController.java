package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.ApiResponse.ApiResponse;
import com.example.thamernumbers.DTOsOut.ProductDTOsOut;
import com.example.thamernumbers.Models.Product;
import com.example.thamernumbers.Services.ProductsServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/product")
@RequiredArgsConstructor
public class ProductsController {

    private final ProductsServices productsServices;

    @GetMapping("/get-all")
    public ResponseEntity<List<ProductDTOsOut>> getAllProducts(){
        List<ProductDTOsOut> products;
        products = productsServices.getAllProducts();
        return ResponseEntity.ok(products);
    }

    @GetMapping("/get-by-id/{id}")
    public ResponseEntity<ProductDTOsOut> getProductById(@PathVariable  Integer id){
        ProductDTOsOut product;
        product = productsServices.getProductById(id);
        return  ResponseEntity.ok(product);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addProduct(@RequestBody @Valid Product product){
        System.out.println((product.getCoffeeBean().getId()));
        productsServices.addProduct(product);
        return ResponseEntity.status(200).body( new ApiResponse("Successfully added product"));
    }




}
