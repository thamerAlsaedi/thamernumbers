package com.example.thamernumbers.Controllers;

import com.example.thamernumbers.Models.Cafes;
import com.example.thamernumbers.Services.CafesServices;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/v1/cafes")
@RequiredArgsConstructor
public class CafesController {

    private final CafesServices cafesServices;

   //  EndPoint To Get all cafés
    @GetMapping("/get-all-cafes")
    public List<Cafes> getAllCafes(){
        List<Cafes> cafes = cafesServices.getAllCafes();
        return cafes;
    }



}
