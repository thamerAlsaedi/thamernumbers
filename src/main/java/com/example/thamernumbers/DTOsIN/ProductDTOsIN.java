package com.example.thamernumbers.DTOsIN;

import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Models.CoffeeBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOsIN {

    @NotEmpty(message = "Name must not be empty")
    private String name;

    @NotNull(message = "Price is required")
    private Double price;

    @NotNull(message = "CoffeeBean ID is required")
    private Integer coffeeBeanId;

    @NotNull(message = "Cafe ID is required")
    private Integer cafeId;
}
