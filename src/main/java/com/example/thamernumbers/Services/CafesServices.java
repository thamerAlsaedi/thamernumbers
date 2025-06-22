package com.example.thamernumbers.Services;

import com.example.thamernumbers.Models.Cafes;
import com.example.thamernumbers.Repositories.CafesRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CafesServices {

    private final CafesRepository cafesRepository;

    // in this method we retrieve all record from Cafés table
     public List<Cafes> getAllCafes(){
        return cafesRepository.findAll();
    }

    // in this method we retrieve record By ID from Cafés table
    public Cafes getCafesById(int id){
         return cafesRepository.findById(id).get();
    }

    public void addCafe(Cafes cafes){
         cafesRepository.save(cafes);
    }

    public void updateCafe(Cafes cafes, int IDOfCafes){



         cafesRepository.save(cafes);
    }

    public void deleteCafe(int id){
         cafesRepository.deleteById(id);
    }




}
