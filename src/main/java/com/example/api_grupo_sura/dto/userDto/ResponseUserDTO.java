package com.example.api_grupo_sura.dto.userDto;

import com.example.api_grupo_sura.dto.vehicleDto.ResponseVehicleDTO;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
public class ResponseUserDTO {

    private Long id;
    private String cedula;
    private String name;
    private String phone;
    private String email;
    private String address;
    private LocalDate birthDate;
    private List<ResponseUserVehicleDTO> vehicles;

}
