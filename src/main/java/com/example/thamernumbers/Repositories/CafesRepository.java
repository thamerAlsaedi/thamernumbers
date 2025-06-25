package com.example.thamernumbers.Repositories;

import com.example.thamernumbers.Models.Cafe;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CafesRepository extends JpaRepository<Cafe,Integer> {

    Cafe getCafesById(Integer id);


}
