package com.example.thamernumbers.DTOsOut;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public class CafeDTOsOut {
    private Integer id;
    private String name;
    private String city;
    private String address;

}
