package com.example.thamernumbers.Controllers;
import com.example.thamernumbers.ApiResponse.ApiResponse;
import com.example.thamernumbers.DTOsOut.CafeDTOsOut;
import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Services.CafesServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
@RestController
@RequestMapping("/api/v1/cafe")
@RequiredArgsConstructor
public class CafesController {

    private final CafesServices cafesServices;

    /**
     * Retrieves all cafés from the system.
     * <p>
     * This endpoint returns a list of all cafés represented as CafeDTOsOut objects.
     * The cafesServices fetches all cafe records from the database.
     *
     * @return a List of CafeDTOsOut objects containing details of all cafés
     */
    @GetMapping("/get-all")
    public List<CafeDTOsOut> getAllCafes() {
        return cafesServices.getAllCafes();
    }

    /**
     * Retrieves a cafe by its ID.
     * <p>
     * This endpoint accepts the ID of the cafe as a path variable and returns the corresponding
     * CafeDTOsOut object. The cafesServices fetches the cafe details from the system.
     * Returns a success response with HTTP status 200 and the cafe data.
     *
     * @param CafeId the ID of the cafe to be retrieved
     * @return ResponseEntity containing the CafeDTOsOut object with the cafe details
     */
    @GetMapping("/get-by-id/{CafeId}")
    public ResponseEntity<CafeDTOsOut> getCafeById(@PathVariable Integer CafeId){
        CafeDTOsOut cafe = cafesServices.getCafesById(CafeId);
        return ResponseEntity.ok(cafe);
    }
    /**
     * Adds a new cafe to the system.
     * <p>
     * This endpoint accepts a valid Cafe object in the request body and persists it using the cafesServices.
     * Returns a success response with HTTP status 200 upon successful addition.
     *
     * @param cafe the Cafe object to be added, validated to ensure required fields are provided
     * @return ResponseEntity containing an ApiResponse with a success message
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCafe(@RequestBody @Valid Cafe cafe ){
        cafesServices.addCafe(cafe);
        return ResponseEntity.status(200).body(new ApiResponse("Cafe added successfully"));
    }

    /**
     * Updates an existing cafe in the system.
     * <p>
     * This endpoint accepts a valid Cafe object in the request body and the ID of the cafe to be updated
     * as a path variable. The cafesServices updates the specified cafe with the provided details.
     * Returns a success response with HTTP status 200 upon successful update.
     *
     * @param cafeId the ID of the cafe to be updated
     * @param cafe the Cafe object containing updated details, validated to ensure required fields are provided
     * @return ResponseEntity containing an ApiResponse with a success message
     **/
    @PutMapping("/update/{cafeId}")
    public ResponseEntity<ApiResponse> updateCafe(@PathVariable Integer cafeId, @RequestBody @Valid Cafe cafe ){
        cafesServices.updateCafe(cafe,cafeId);
        return ResponseEntity.status(200).body(new ApiResponse("Cafe updated successfully"));
    }
    /**
     * Deletes a cafe from the system.
     * <p>
     * This endpoint accepts the ID of the cafe to be deleted as a path variable.
     * The cafesServices removes the specified cafe from the system.
     * Returns a success response with HTTP status 200 upon successful deletion.
     *
     * @param cafeId the ID of the cafe to be deleted
     * @return ResponseEntity containing an ApiResponse with a success message
     */
    @DeleteMapping("/delete/{cafeId}")
    public ResponseEntity<ApiResponse> deleteCafe(@PathVariable Integer cafeId){
        cafesServices.deleteCafe(cafeId);
        return ResponseEntity.status(200).body(new ApiResponse("Cafe deleted successfully"));
    }









}
