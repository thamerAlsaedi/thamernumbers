package com.example.thamernumbers.DTOsOut;

import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Models.CoffeeBean;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductDTOsOut {

    private Integer id;
    private String name;
    private double price;

}
