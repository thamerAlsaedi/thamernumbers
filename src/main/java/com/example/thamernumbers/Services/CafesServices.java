package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.DTOsOut.CafeDTOsOut;
import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Repositories.CafesRepository;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CafesServices {

    private final CafesRepository cafesRepository;

    // in this method we retrieve all record from Cafés table
     public List<CafeDTOsOut> getAllCafes(){

         List<Cafe> cafes = cafesRepository.findAll();
          List<CafeDTOsOut> cafeDTOsOutList = new ArrayList<>();

          for (Cafe cafe1 : cafes) {
              CafeDTOsOut cafeDTOsOut = new CafeDTOsOut(cafe1.getId(),cafe1.getName(),cafe1.getAddress(),cafe1.getCity());
              cafeDTOsOutList.add(cafeDTOsOut);
          }
        return cafeDTOsOutList;
    }


    /*
    This function does a very specific job:
    retrieve information about a particular coffee shop from the database using its unique identifier (ID).
    */
    public CafeDTOsOut getCafesById(Integer CafeId){

        Cafe cafe = cafesRepository.getCafesById(CafeId);

        if(cafe == null){
            throw new ApiException("Cafe not found with id: " + CafeId);
        }
        CafeDTOsOut cafeDTOsOut = new CafeDTOsOut();

        cafeDTOsOut.setId(cafe.getId());
        cafeDTOsOut.setName(cafe.getName());
        cafeDTOsOut.setCity(cafe.getCity());
        cafeDTOsOut.setAddress(cafe.getAddress());
        return cafeDTOsOut;
    }


    /*
    This function does a very specific job:
    add a new a particular coffee shop.
    */
    public void addCafe(Cafe newCafe) {

        List<Cafe> cafes = cafesRepository.findAll();

        for (Cafe cafe1 : cafes) {

            if (cafe1.getPhoneNumber().equals(newCafe.getPhoneNumber()) ) {
                throw new ApiException("PhoneNumber already exists");
            }
            if(cafe1.getEmail().equals(newCafe.getEmail())){
                throw new ApiException("Email already exists");
            }
            if(cafe1.getCafe_cr().equals(newCafe.getCafe_cr())){
                throw new ApiException("Cafe's Commercial Registration (CR)  already exists");
            }
        }

         cafesRepository.save(newCafe);
    }


    public void updateCafe(Cafe newUpdataCafe, Integer cafeId){
        Cafe existingCafe = cafesRepository.getCafesById(cafeId);
        if(existingCafe == null){
            throw new ApiException("Cafe not found with id: " + cafeId);
        }

        List<Cafe> cafes = cafesRepository.findAll();

        for (Cafe cafe1 : cafes) {

            if (cafe1.getPhoneNumber().equals(newUpdataCafe.getPhoneNumber()) ) {
                throw new ApiException("PhoneNumber already exists");
            }
            if(cafe1.getEmail().equals(newUpdataCafe.getEmail())){
                throw new ApiException("Email already exists");
            }
            if(cafe1.getCafe_cr().equals(newUpdataCafe.getCafe_cr())){
                throw new ApiException("Cafe's Commercial Registration (CR)  already exists");
            }
        }

        existingCafe.setName(newUpdataCafe.getName());
        existingCafe.setAddress(newUpdataCafe.getAddress());
        existingCafe.setCity(newUpdataCafe.getCity());
        existingCafe.setProducts(newUpdataCafe.getProducts());

         cafesRepository.save(existingCafe);
    }


    /*public void deleteCafe(Integer cafeId ){

         Cafe cafe = cafesRepository.getCafesById(cafeId);
         if(cafe == null){
             throw new ApiException("Cafe not found with id: " + cafeId);
         }
         cafesRepository.delete(cafe);
    }*/




}
