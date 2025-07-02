package com.example.thamernumbers.Models;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Set;

/**
 * Entity class representing a CoffeeBean.
 * Contains details about coffee origin, type, flavor, and popularity.
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "coffeeBean")
public class CoffeeBean {

    /**
     * The primary key for the CoffeeBean entity.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * The country of origin for the coffee bean.
     * Must not be empty and has a max length of 100 characters.
     */
    @NotEmpty(message = "Country must not be empty")
    @Size(max = 100)
    @Column(nullable = false)
    private String country;

    /**
     * The specific variety name of the coffee bean.
     * Example: Yirgacheffe, Bourbon, SL28.
     */
    @NotEmpty(message = "Variety name must not be empty")
    @Size(max = 100)
    @Column(nullable = false)
    private String varietyName;

    /**
     * The type of coffee bean (e.g., Arabica, Robusta, Liberica).
     */
    @NotEmpty(message = "CoffeeBean type must not be empty")
    @Size(max = 50)
    @Column(nullable = false)
    private String beanType;

    /**
     * The flavor notes typically associated with this coffee bean.
     * Example: fruity, floral, chocolatey.
     */
    @NotEmpty(message = "Flavor notes must not be empty")
    @Size(max = 255)
    @Column(nullable = false)
    private String flavorNotes;

    /**
     * Indicates the global/regional popularity of the bean.
     * Example: Global, Specialty, Niche.
     */
    @NotEmpty(message = "Popularity must not be empty")
    @Size(max = 50)
    @Column(nullable = false)
    private String popularity;

    /**
     * One-to-many relationship with products that use this coffee bean.
     */
    @OneToMany(mappedBy = "coffeeBean", cascade = CascadeType.ALL)

    private Set<Product> products;
}
