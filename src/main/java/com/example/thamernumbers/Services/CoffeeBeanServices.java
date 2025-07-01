package com.example.thamernumbers.Services;

import com.example.thamernumbers.ApiResponse.ApiException;
import com.example.thamernumbers.DTOsOut.CoffeeBeanDTOsOut;
import com.example.thamernumbers.Models.CoffeeBean;
import com.example.thamernumbers.Repositories.CoffeeBeanRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for handling business logic related to CoffeeBean entity.
 */
@Service
@RequiredArgsConstructor
public class CoffeeBeanServices {

    private final CoffeeBeanRepository coffeeBeanRepository;

    /**
     * Retrieves all coffee beans from the database.
     *
     * @return List of CoffeeBean objects
     * @throws ApiException if no beans are found
     */
    public List<CoffeeBeanDTOsOut> getAllBeans() {
        List<CoffeeBean> coffeeBeans = coffeeBeanRepository.findAll();
        if (coffeeBeans.isEmpty()) {
            throw new ApiException("No coffeeBeans found");
        }
        List<CoffeeBeanDTOsOut> coffeeBeans1 = new ArrayList<>();
        for (CoffeeBean coffeeBean : coffeeBeans) {
            CoffeeBeanDTOsOut coffeeBeanDTOsOut = new CoffeeBeanDTOsOut();
            coffeeBeanDTOsOut.setBeanType(coffeeBean.getBeanType());
            coffeeBeanDTOsOut.setVarietyName(coffeeBean.getVarietyName());
            coffeeBeanDTOsOut.setCountry(coffeeBean.getCountry());
            coffeeBeanDTOsOut.setFlavorNotes(coffeeBean.getFlavorNotes());
            coffeeBeanDTOsOut.setPopularity(coffeeBean.getPopularity());
            coffeeBeanDTOsOut.setPopularity(coffeeBean.getPopularity());
            coffeeBeans1.add(coffeeBeanDTOsOut);
        }
        return coffeeBeans1;
    }

    /**
     * Retrieves a coffee bean by its ID.
     *
     * @param coffeeBeanId the ID of the coffee bean
     * @return the CoffeeBean object
     * @throws ApiException if not found
     */
    public CoffeeBeanDTOsOut getCoffeeBeanById(Integer coffeeBeanId) {
        CoffeeBean coffeeBean = coffeeBeanRepository.getCoffeeBeanById(coffeeBeanId);
        if (coffeeBean == null) {
            throw new ApiException("CoffeeBean not found");
        }
        return new CoffeeBeanDTOsOut(
                coffeeBean.getCountry(),
                coffeeBean.getVarietyName(),
                coffeeBean.getBeanType(),
                coffeeBean.getFlavorNotes(),
                coffeeBean.getPopularity()
        );
    }

    /**
     * Adds a single coffee bean to the database.
     *
     * @param coffeeBean the CoffeeBean object to add
     */
    public void addBean(CoffeeBean coffeeBean) {
        coffeeBeanRepository.save(coffeeBean);
    }

    /**
     * Adds multiple coffee beans to the database.
     *
     * @param coffeeBeans list of CoffeeBean objects
     */
    public void addMultipleCoffeeBeans(List<CoffeeBean> coffeeBeans) {
        coffeeBeanRepository.saveAll(coffeeBeans);
    }

    /**
     * Updates an existing coffee bean by its ID.
     *
     * @param coffeeBeanId   the ID of the coffee bean to update
     * @param newCoffeeBean  the new CoffeeBean data
     * @throws ApiException if the bean does not exist
     */
    public void updateCoffeeBean(Integer coffeeBeanId, CoffeeBean newCoffeeBean) {
        CoffeeBean existingCoffeeBean = coffeeBeanRepository.getCoffeeBeanById(coffeeBeanId);
        if (existingCoffeeBean == null) {
            throw new ApiException("CoffeeBean not found");
        }

        existingCoffeeBean.setCountry(newCoffeeBean.getCountry());
        existingCoffeeBean.setBeanType(newCoffeeBean.getBeanType());
        existingCoffeeBean.setPopularity(newCoffeeBean.getPopularity());
        existingCoffeeBean.setVarietyName(newCoffeeBean.getVarietyName());

        coffeeBeanRepository.save(existingCoffeeBean);  // fixed from saving newCoffeeBean
    }

    /**
     * Deletes a coffee bean by its ID.
     *
     * @param coffeeBeanId the ID of the coffee bean to delete
     * @throws ApiException if not found
     */
    public void deleteCoffeeBean(Integer coffeeBeanId) {
        CoffeeBean coffeeBean = coffeeBeanRepository.getCoffeeBeanById(coffeeBeanId);
        if (coffeeBean == null) {
            throw new ApiException("CoffeeBean not found");
        }
        coffeeBeanRepository.deleteById(coffeeBeanId);
    }
}
