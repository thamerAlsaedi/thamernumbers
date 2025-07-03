package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.DTOsIN.ProductDTOsIN;
import com.example.thamernumbers.Models.Product;
import com.example.thamernumbers.Repositories.ProductsRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class ProductsServicesTest {

    private ProductsRepository productsRepository;
    private ProductsServices productsServices;

    @BeforeEach
    void setUp() {
        productsRepository = mock(ProductsRepository.class);
        productsServices = new ProductsServices(productsRepository, null, null); // تمرر null إذا ما تحتاج الريبو الثاني
    }

    @Test
    void checkIfProductExists_shouldThrowException_whenProductExists() {
        // Arrange
        ProductDTOsIN dto = new ProductDTOsIN();
        dto.setName("Latte");
        dto.setCafeId(1);

        when(productsRepository.findByNameAndCafeId("Latte", 1))
                .thenReturn(Optional.of(new Product()));

        // Act + Assert
        ApiException exception = assertThrows(ApiException.class, () -> {
            productsServices.checkIfProductExists(dto);
        });

        assertEquals("Product with the same name already exists in the same cafe", exception.getMessage());
    }

    @Test
    void checkIfProductExists_shouldDoNothing_whenProductDoesNotExist() {
        // Arrange
        ProductDTOsIN dto = new ProductDTOsIN();
        dto.setName("Cappuccino");
        dto.setCafeId(2);

        when(productsRepository.findByNameAndCafeId("Cappuccino", 2))
                .thenReturn(Optional.empty());

        // Act & Assert (no exception should be thrown)
        assertDoesNotThrow(() -> {
            productsServices.checkIfProductExists(dto);
        });
    }
}