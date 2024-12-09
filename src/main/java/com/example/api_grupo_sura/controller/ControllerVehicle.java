package com.example.api_grupo_sura.controller;


import com.example.api_grupo_sura.dto.vehicleDto.RequestUpdateVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.RequestVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.ResponseVehicleDTO;
import com.example.api_grupo_sura.service.impl.VehicleService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/vehicles")
public class ControllerVehicle {

    private final VehicleService vehicleService;

    public ControllerVehicle(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @PostMapping
    public ResponseEntity<ResponseVehicleDTO> createVehicle(@RequestBody RequestVehicleDTO requestVehicleDTO){
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(this.vehicleService.createVehicle(requestVehicleDTO));
    }

    @GetMapping
    public ResponseEntity<List<ResponseVehicleDTO>> getAllVehicle(){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.vehicleService.findAllVehicle());
    }

    @GetMapping("/{id}")
    public ResponseEntity<ResponseVehicleDTO> getVehicleById(@RequestBody Long id){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.vehicleService.findVehicleById(id));
    }

    @PutMapping
    public ResponseEntity<ResponseVehicleDTO> updatedVehicle(@RequestBody RequestUpdateVehicleDTO requestUpdateVehicleDTO){
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(this.vehicleService.updateVehicle(requestUpdateVehicleDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVehicleById(@PathVariable Long id){
        this.vehicleService.deleteVehicleById(id);
        return ResponseEntity.status(HttpStatus.OK).build();
    }

}
