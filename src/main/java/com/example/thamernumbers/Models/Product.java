package com.example.thamernumbers.Models;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;

import lombok.NoArgsConstructor;


@Data

@AllArgsConstructor

@NoArgsConstructor
@Entity
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private double price;


    @ManyToOne
    @JsonIgnore
    private Bean bean;

    @ManyToOne
    @JsonIgnore
    private Cafe cafe;

}
