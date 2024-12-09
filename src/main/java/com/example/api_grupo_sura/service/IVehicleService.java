package com.example.api_grupo_sura.service;

import com.example.api_grupo_sura.dto.vehicleDto.RequestUpdateVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.RequestVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.ResponseVehicleDTO;

import java.util.List;

public interface IVehicleService {

    ResponseVehicleDTO createVehicle(RequestVehicleDTO requestVehicleDTO);
    List<ResponseVehicleDTO> findAllVehicle();
    ResponseVehicleDTO findVehicleById(Long id);
    ResponseVehicleDTO updateVehicle(RequestUpdateVehicleDTO requestUpdateVehicleDTO);
    void deleteVehicleById(Long id);
}
