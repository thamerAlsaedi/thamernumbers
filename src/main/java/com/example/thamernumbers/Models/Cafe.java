package com.example.thamernumbers.Models;


import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.Set;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Cafe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotEmpty(message = "Cafe name cannot be Empty")
    @Size(min = 2, max = 100, message = "Cafe name must be between 2 and 50 characters")
    @Column(nullable = false)
    private String name;

    @NotEmpty(message="Cafe Email cannot be Empty")
    @Email(message="Invalid email format")
    @Column(nullable = false, unique = true)
    private String email;

    @Pattern(regexp = "^[0-9]{10}$", message = "Phone number must be a valid format (e.g., 0123456789)")
    @Column(nullable = false, unique = true)
    private String phoneNumber;

    @NotEmpty(message = "Cafe CR cannot be Empty")
    @Pattern(regexp = "^[A-Za-z0-9]{3,20}$", message = "Cafe CR must be alphanumeric and between 3 to 20 characters")
    @Column(nullable = false, unique = true)
    private String cafe_cr;

    @NotEmpty(message="City cannot be empty")
    @Column(nullable = false)
    @Size(max = 100, message = "The city's name must not exceed 100 characters.")
    private String  city;

    @Column(nullable = false)
    private String address;

    @OneToMany(mappedBy = "cafe", cascade = CascadeType.ALL)
    private Set<Product> products;


}
