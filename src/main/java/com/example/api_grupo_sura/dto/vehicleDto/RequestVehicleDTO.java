package com.example.api_grupo_sura.dto.vehicleDto;

import com.example.api_grupo_sura.entity.User;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Data
public class RequestVehicleDTO {

    private String model;
    private String brand;
    private String engineCapacity;
    private String color;
    private String description;
    private Integer accidentCount;
    private Long userId;
}
