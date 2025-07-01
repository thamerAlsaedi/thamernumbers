package com.example.thamernumbers.DTOsOut;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CoffeeBeanDTOsOut {

    @NotEmpty(message = "Country must not be empty")
    @Size(max = 100)
    private String country;

    @NotEmpty(message = "Variety name must not be empty")
    @Size(max = 100)
    private String varietyName;

    @NotEmpty(message = "CoffeeBean type must not be empty")
    @Size(max = 50)
    private String beanType;

    @NotEmpty(message = "Flavor notes must not be empty")
    @Size(max = 255)
    private String flavorNotes;

    @NotEmpty(message = "Popularity must not be empty")
    @Size(max = 50)
    private String popularity;
}
