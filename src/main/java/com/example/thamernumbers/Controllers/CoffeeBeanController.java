package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.ApiResponse.ApiResponse;
import com.example.thamernumbers.DTOsOut.CoffeeBeanDTOsOut;
import com.example.thamernumbers.Models.CoffeeBean;
import com.example.thamernumbers.Services.CoffeeBeanServices;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing coffee bean operations.
 * Handles CRUD endpoints for CoffeeBean entity.
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/coffee-bean")
public class CoffeeBeanController {

    private final CoffeeBeanServices coffeeBeanServices;

    /**
     * GET /get-all : Retrieve all coffee beans.
     *
     * @return List of CoffeeBean objects.
     */
    @GetMapping("/get-all")
    public ResponseEntity<List<CoffeeBeanDTOsOut>> getAllBeans(){
        List<CoffeeBeanDTOsOut> coffeeBeans = coffeeBeanServices.getAllBeans();
        return ResponseEntity.ok(coffeeBeans);
    }

    /**
     * GET /get-coffee-bean-by-id/{coffeeBeanId} : Retrieve a coffee bean by its ID.
     *
     * @param coffeeBeanId The ID of the coffee bean.
     * @return The matching CoffeeBean object.
     */
    @GetMapping("/get-coffee-bean-by-id/{coffeeBeanId}")
    public ResponseEntity<CoffeeBeanDTOsOut> getCoffeeBeanById(@PathVariable Integer coffeeBeanId){
        CoffeeBeanDTOsOut cb = coffeeBeanServices.getCoffeeBeanById(coffeeBeanId);
        return ResponseEntity.ok(cb);
    }

    /**
     * POST /add : Add a single new coffee bean.
     *
     * @param coffeeBean The CoffeeBean object to add (validated).
     * @return Success message wrapped in ApiResponse.
     */
    @PostMapping("/add")
    public ResponseEntity<ApiResponse> addBean(@RequestBody @Valid CoffeeBean coffeeBean){
        coffeeBeanServices.addBean(coffeeBean);
        return ResponseEntity.ok(new ApiResponse("Successfully added CoffeeBean"));
    }

    /**
     * POST /addMultiple : Add multiple coffee beans at once.
     *
     * @param coffeeBeans List of CoffeeBean objects to add (validated).
     * @return Success message wrapped in ApiResponse.
     */
    @PostMapping("/addMultiple")
    public ResponseEntity<ApiResponse> addMultipleBeans(@RequestBody @Valid List<CoffeeBean> coffeeBeans){
        coffeeBeanServices.addMultipleCoffeeBeans(coffeeBeans);
        return ResponseEntity.ok(new ApiResponse("Successfully added CoffeeBeans"));
    }

    /**
     * PUT /update/{CoffeeBeanId} : Update an existing coffee bean.
     *
     * @param CoffeeBeanId The ID of the coffee bean to update.
     * @param coffeeBean The new data for the coffee bean (validated).
     * @return Success message wrapped in ApiResponse.
     */
    @PutMapping("/update/{CoffeeBeanId}")
    public ResponseEntity<ApiResponse> updateBean(@PathVariable Integer CoffeeBeanId,
                                                  @RequestBody @Valid CoffeeBean coffeeBean){
        coffeeBeanServices.updateCoffeeBean(CoffeeBeanId, coffeeBean);
        return ResponseEntity.ok(new ApiResponse("Successfully updated CoffeeBean"));
    }

    /**
     * DELETE /delete-coffee-bean-by-id/{CoffeeBeanId} : Delete a coffee bean by ID.
     *
     * @param CoffeeBeanId The ID of the coffee bean to delete.
     * @return Success message wrapped in ApiResponse.
     */
    @DeleteMapping("/delete-coffee-bean-by-id/{CoffeeBeanId}")
    public ResponseEntity<ApiResponse> deleteCoffeeBeanById(@PathVariable Integer CoffeeBeanId){
        coffeeBeanServices.deleteCoffeeBean(CoffeeBeanId);
        return ResponseEntity.ok(new ApiResponse("Successfully deleted CoffeeBean"));
    }
}
