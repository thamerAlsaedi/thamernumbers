package com.example.thamernumbers.Models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cafes {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String  city;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private Set<Products> products;


}
