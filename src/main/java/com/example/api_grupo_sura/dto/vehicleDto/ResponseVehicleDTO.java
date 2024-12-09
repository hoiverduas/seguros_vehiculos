package com.example.api_grupo_sura.dto.vehicleDto;

import lombok.Data;

@Data
public class ResponseVehicleDTO {

    private Long id;
    private String model;
    private String brand;
    private String engineCapacity;
    private String color;
    private String description;
    private Integer accidentCount;
    private Long userId;
}
