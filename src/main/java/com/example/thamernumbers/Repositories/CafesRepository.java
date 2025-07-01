package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.Cafe;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CafesRepository extends JpaRepository<Cafe,Integer> {

    Cafe getCafesById(Integer id);

   List<Cafe> getCafeByCityContaining(String cafeCity);

    Cafe getCafeByNameContaining(@NotEmpty(message = "Cafe name cannot be Empty") @Size(min = 2, max = 100, message = "Cafe name must be between 2 and 50 characters") String name);
}
