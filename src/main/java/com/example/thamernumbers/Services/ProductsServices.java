package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.DTOsOut.ProductDTOsOut;
import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Models.CoffeeBean;
import com.example.thamernumbers.Models.Product;
import com.example.thamernumbers.Repositories.CafesRepository;
import com.example.thamernumbers.Repositories.CoffeeBeanRepository;
import com.example.thamernumbers.Repositories.ProductsRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Service class for managing products.
 */
@Service
@RequiredArgsConstructor
public class ProductsServices {

    @Autowired
    private final ProductsRepository productsRepository;

    @Autowired
    private final CafesRepository cafesRepository;

    @Autowired
    private final CoffeeBeanRepository coffeeBeanRepository;

    /**
     * Retrieves all products from the repository.
     *
     * @return List of ProductDTOsOut containing product information.
     * @throws ApiException if no products are found.
     */
    public List<ProductDTOsOut> getAllProducts() {
        List<Product> products = productsRepository.findAll();

        if (products.isEmpty()) {
            throw new ApiException("No products found");
        }

        return products.stream()
                .map(p -> new ProductDTOsOut(
                        p.getId(),
                        p.getName(),
                        p.getPrice()
                ))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a product by its ID.
     *
     * @param product_Id the ID of the product to retrieve.
     * @return ProductDTOsOut containing the product information.
     * @throws ApiException if the product is not found.
     */
    public ProductDTOsOut getProductById(Integer product_Id) {
        Product product = productsRepository.getProductsById(product_Id);
        if (product == null) {
            throw new ApiException("Product not found with id " + product_Id);
        }

        return new ProductDTOsOut(
                product.getId(),
                product.getName(),
                product.getPrice()
        );
    }

    /**
     * Adds a new product after validating related entities and duplicates.
     *
     * @param product the product to add.
     * @throws ApiException if required fields are missing,
     *                      related entities do not exist,
     *                      or a duplicate product already exists in the same cafe.
     */
    public void addProduct(Product product) {

        if (product.getCoffeeBean() == null || product.getCoffeeBean().getId() == null) {
            throw new ApiException("CoffeeBean is required and must include a valid ID");
        }

        if (product.getCafe() == null || product.getCafe().getId() == null) {
            throw new ApiException("Cafe is required and must include a valid ID");
        }

        CoffeeBean coffeeBean = coffeeBeanRepository.findById(product.getCoffeeBean().getId())
                .orElseThrow(() -> new ApiException("CoffeeBean not found"));

        Cafe cafe = cafesRepository.findById(product.getCafe().getId())
                .orElseThrow(() -> new ApiException("Cafe not found"));

        if (productsRepository.findByNameAndCafeId(product.getName(), product.getCafe().getId()).isPresent()) {
            throw new ApiException("Product with the same name already exists in the same cafe");
        }

        product.setCafe(cafe);
        product.setCoffeeBean(coffeeBean);

        productsRepository.save(product);
    }

    /**
     * Updates an existing product.
     *
     * @param product the product data to update.
     * @throws ApiException if the product is not found.
     */
    public void updateProduct(Product product) {
        Product existingProduct = productsRepository.findById(product.getId())
                .orElseThrow(() -> new ApiException("Product not found"));

        existingProduct.setName(product.getName());
        existingProduct.setPrice(product.getPrice());
        existingProduct.setCoffeeBean(product.getCoffeeBean());
        existingProduct.setCafe(product.getCafe());

        productsRepository.save(existingProduct);
    }

    /**
     * Deletes a product by its ID.
     *
     * @param id the ID of the product to delete.
     * @throws ApiException if the product is not found.
     */
    public void deleteProduct(Integer id) {
        Product product = productsRepository.getProductsById(id);
        if (product == null) {
            throw new ApiException("Product not found");
        }
        productsRepository.delete(product);
    }

}
