package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.CoffeeBean;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CoffeeBeanRepository extends JpaRepository<CoffeeBean, Integer> {


    CoffeeBean getCoffeeBeanById(Integer id);
}
