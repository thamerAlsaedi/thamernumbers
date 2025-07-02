package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.DTOsOut.CafeDTOsOut;
import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Repositories.CafesRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Service layer responsible for handling all business logic related to cafés.
 * <p>
 * This class interacts with the {@code CafesRepository} to perform operations such as:
 * retrieving all cafés, finding a café by ID, city, or name, adding new cafés,
 * updating existing cafés, and deleting them.
 * <p>
 * It also includes validation logic to prevent duplicate entries based on
 * phone number, email, or commercial registration (CR).
 *
 * @author Thamer
 * @since 1.0
 */

@Service
@RequiredArgsConstructor
public class CafesServices {

    private final CafesRepository cafesRepository;


    // in this method we retrieve all record from Cafés table
     public List<CafeDTOsOut> getAllCafes(){
         List<Cafe> cafes = cafesRepository.findAll();
         if(cafes.isEmpty()){
            throw new ApiException("No Cafes found");
         }
         return cafes.stream()
                 .map(cafe -> new CafeDTOsOut(
                         cafe.getId(),
                         cafe.getName(),
                         cafe.getCity(),     // make sure this matches your DTO constructor
                         cafe.getAddress()))
                 .collect(Collectors.toList());
    }

    /**
     * Retrieves a café by its unique identifier.
     * <p>
     * This endpoint returns the details of a specific café represented as a CafeDTOsOut object.
     * The cafesRepository fetches the café record from the database using the provided ID.
     * If no café is found with the given ID, an ApiException is thrown.
     *
     * @param CafeId the unique identifier of the café to retrieve
     * @return a CafeDTOsOut object containing the details of the requested café
     * @throws ApiException if no café is found with the provided ID
     */
    public CafeDTOsOut getCafesById(Integer CafeId){
        Cafe cafe = cafesRepository.getCafesById(CafeId);

        if(cafe == null){
            throw new ApiException("Cafe not found with id: " + CafeId);
        }

        return new CafeDTOsOut(
                cafe.getId(),
                cafe.getName(),
                cafe.getCity(),
                cafe.getAddress()
        );
    }

    /**
     * Retrieves a café by its city name.
     * <p>
     * This method searches for a single café located in the specified city.
     * If no café is found for the given city, an ApiException is thrown.
     * On success, it returns a CafeDTOsOut object containing the café's basic details.
     *
     * @param CafeCity the name of the city to search for a café
     * @return a CafeDTOsOut object containing the details of the café in the specified city
     * @throws ApiException if no café is found in the provided city
     */
    public List<CafeDTOsOut> getCafesByCity(String CafeCity){
        List<Cafe> cafes = cafesRepository.getCafeByCityContaining(CafeCity);
        if(cafes.isEmpty()){
            throw new ApiException("Cafe not found with City: " + CafeCity);
        }
        List<CafeDTOsOut> cafeDTOsOutList = new ArrayList<>();

        return cafes.stream()
                .map(cafe -> new CafeDTOsOut(
                        cafe.getId(),
                        cafe.getName(),
                        cafe.getCity(),
                        cafe.getAddress()))
                .collect(Collectors.toList());
    }

    /**
     * Retrieves a café by its name.
     * <p>
     * This method searches the database for a single café that matches the provided name.
     * If no matching café is found, an {@link ApiException} is thrown.
     * On success, the method returns a {@link CafeDTOsOut} object containing the café's basic information.
     *
     * @param CafeName the name of the café to retrieve
     * @return a {@link CafeDTOsOut} object with the details of the café
     * @throws ApiException if no café is found with the given name
     */
    public CafeDTOsOut getCafesByName(String CafeName){

        Cafe cafe = cafesRepository.getCafeByNameContaining(CafeName);

        if(cafe == null ){
            throw new ApiException("Cafe not found with Cafe Name: " + CafeName);
        }
        return new CafeDTOsOut(
                        cafe.getId(),
                        cafe.getName(),
                        cafe.getCity(),
                        cafe.getAddress());
    }


    /**
     * Adds a new café to the system.
     * <p>
     * This method checks whether a café with the same phone number, email, or commercial registration (CR)
     * already exists in the database. If any duplicate is found, an ApiException is thrown.
     * If no duplicates are found, the new café is saved to the database.
     *
     * @param newCafe the Cafe object containing the details of the café to be added
     * @throws ApiException if the phone number, email, or commercial registration already exists
     */
    public void addCafe(Cafe newCafe) {
        List<Cafe> cafes;
        cafes = cafesRepository.findAll();
        for (Cafe currentCafe : cafes) {

            if (currentCafe.getPhoneNumber().equals(newCafe.getPhoneNumber()) ) {
                throw new ApiException("PhoneNumber already exists");
            }
            if(currentCafe.getEmail().equals(newCafe.getEmail())){
                throw new ApiException("Email already exists");
            }
            if(currentCafe.getCafe_cr().equals(newCafe.getCafe_cr())){
                throw new ApiException("Cafe's Commercial Registration (CR)  already exists");
            }
        }
         cafesRepository.save(newCafe);
    }

    /**
     * Updates the details of an existing café.
     * <p>
     * This method first checks if a café with the given ID exists in the database.
     * If not, an ApiException is thrown. It also ensures that the updated phone number,
     * email, or commercial registration (CR) do not already exist in another café record.
     * If duplicates are found, the method throws an ApiException accordingly.
     * Once validations pass, it updates the café's name, address, city, and products,
     * and saves the updated record to the database.
     *
     * @param updatedCafe a Cafe object containing the updated details
     * @param cafeId the ID of the café to be updated
     * @throws ApiException if the café is not found, or if the updated phone number, email,
     *                      or CR already exists in another record
     */
    public void updateCafe(Cafe updatedCafe, Integer cafeId) {
        Cafe existingCafe = cafesRepository.getCafesById(cafeId);
        if (existingCafe == null) {
            throw new ApiException("Cafe not found with id: " + cafeId);
        }

        List<Cafe> cafes = cafesRepository.findAll();
        for (Cafe otherCafe : cafes) {
            if (otherCafe.getId().equals(cafeId)) {
                continue; // Skip the current cafe being updated
            }

            if (otherCafe.getPhoneNumber().equals(updatedCafe.getPhoneNumber())) {
                throw new ApiException("Phone number already exists");
            }

            if (otherCafe.getEmail().equals(updatedCafe.getEmail())) {
                throw new ApiException("Email already exists");
            }

            if (otherCafe.getCafe_cr().equals(updatedCafe.getCafe_cr())) {
                throw new ApiException("Cafe's Commercial Registration (CR) already exists");
            }
        }

    }

    /**
     * Deletes a café by its unique identifier.
     * <p>
     * This method retrieves the café using the provided ID.
     * If the café does not exist, an ApiException is thrown.
     * Otherwise, the café is deleted from the database.
     *
     * @param cafeId the unique identifier of the café to be deleted
     * @throws ApiException if no café is found with the provided ID
     */
    public void deleteCafe(Integer cafeId ){
         Cafe cafe = cafesRepository.getCafesById(cafeId);
         if(cafe == null){
             throw new ApiException("Cafe not found with id: " + cafeId);
         }
         cafesRepository.delete(cafe);
    }




}
