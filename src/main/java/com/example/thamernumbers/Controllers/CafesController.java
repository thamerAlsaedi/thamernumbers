package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.ApiResponse.ApiResponse;
import com.example.thamernumbers.DTOsOut.CafeDTOsOut;
import com.example.thamernumbers.Models.Cafe;
import com.example.thamernumbers.Services.CafesServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/cafe")
@RequiredArgsConstructor
public class CafesController {

    private final CafesServices cafesServices;

    /*
     This Endpoint does a very specific job:
     To fetch a list of all the cafés from the database.
     */
    @GetMapping("/get-all")
    public List<CafeDTOsOut> getAllCafes() {
        List<CafeDTOsOut> cafes = cafesServices.getAllCafes();
        return cafes;
    }

    /*
    This Endpoint does a very specific job:
    retrieve information about a particular coffee shop from the database using its unique identifier ID.
    * */

    @GetMapping("/get-by-id/{CafeId}")
    public ResponseEntity<CafeDTOsOut> getCafeById(@PathVariable Integer CafeId){
        CafeDTOsOut cafe = cafesServices.getCafesById(CafeId);
        return ResponseEntity.ok(cafe);
    }

    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addCafe(@RequestBody @Valid Cafe cafe ){
        cafesServices.addCafe(cafe);
        return ResponseEntity.status(200).body(new ApiResponse("Cafe added successfully"));
    }

    @PutMapping("/update/{cafeId}")
    public ResponseEntity<ApiResponse> updateCafe(@PathVariable Integer cafeId, @RequestBody @Valid Cafe cafe ){
        cafesServices.updateCafe(cafe,cafeId);
        return ResponseEntity.status(200).body(new ApiResponse("Cafe updated successfully"));
    }









}
