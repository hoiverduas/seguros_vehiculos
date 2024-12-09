package com.example.api_grupo_sura.service.impl;

import com.example.api_grupo_sura.dto.vehicleDto.RequestUpdateVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.RequestVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.ResponseVehicleDTO;
import com.example.api_grupo_sura.entity.User;
import com.example.api_grupo_sura.entity.Vehicle;
import com.example.api_grupo_sura.repository.IUserRepository;
import com.example.api_grupo_sura.repository.IVehicleRepository;
import com.example.api_grupo_sura.service.IVehicleService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class VehicleService implements IVehicleService {


    private final IVehicleRepository vehicleRepository;
    private final IUserRepository userRepository;

    public VehicleService(IVehicleRepository vehicleRepository, IUserRepository userRepository) {
        this.vehicleRepository = vehicleRepository;
        this.userRepository = userRepository;
    }


    @Override
    public ResponseVehicleDTO createVehicle(RequestVehicleDTO requestVehicleDTO) {


        Long userId = requestVehicleDTO.getUserId();
        User user = userRepository.findById(userId)
                .orElseThrow(()->new RuntimeException("no found"));

           Vehicle vehicle = mapToEntity(requestVehicleDTO);
           vehicle.setId(null);
           vehicle.setUser(user);
           this.vehicleRepository.save(vehicle);


        return mapToDto(vehicle) ;
    }

    @Override
    public List<ResponseVehicleDTO> findAllVehicle() {
        List<Vehicle>vehicles= (List<Vehicle>) vehicleRepository.findAll();
        return vehicles.stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseVehicleDTO findVehicleById(Long id) {
        return this.mapToDto(vehicleRepository.findById(id)
                .orElseThrow(()->new RuntimeException("No found")));
    }

    @Override
    public ResponseVehicleDTO updateVehicle(RequestUpdateVehicleDTO requestUpdateVehicleDTO) {
        // Buscar el vehículo existente
        Vehicle existingVehicle = vehicleRepository.findById(requestUpdateVehicleDTO.getId())
                .orElseThrow(() -> new RuntimeException("Vehicle not found"));

        Vehicle vehicle = mapToEntity(requestUpdateVehicleDTO);

        existingVehicle.setAccidentCount(requestUpdateVehicleDTO.getAccidentCount());
        existingVehicle.setBrand(requestUpdateVehicleDTO.getBrand());
        existingVehicle.setColor(requestUpdateVehicleDTO.getColor());
        existingVehicle.setDescription(requestUpdateVehicleDTO.getDescription());
        existingVehicle.setEngineCapacity(requestUpdateVehicleDTO.getEngineCapacity());
        existingVehicle.setModel(requestUpdateVehicleDTO.getModel());

        if (requestUpdateVehicleDTO.getUserId() != null) {
            User user = userRepository.findById(requestUpdateVehicleDTO.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            existingVehicle.setUser(user);
        }

        vehicleRepository.save(existingVehicle);

        return mapToDto(vehicle);

    }




    @Override
    public void deleteVehicleById(Long id) {
         findVehicleById(id);
         this.vehicleRepository.deleteById(id);
    }


    private ResponseVehicleDTO mapToDto(Vehicle vehicle){

        ResponseVehicleDTO responseVehicleDTO = new ResponseVehicleDTO();

        responseVehicleDTO.setUserId(vehicle.getId());
        responseVehicleDTO.setId(vehicle.getId());
        responseVehicleDTO.setBrand(vehicle.getBrand());
        responseVehicleDTO.setColor(vehicle.getColor());
        responseVehicleDTO.setDescription(vehicle.getDescription());
        responseVehicleDTO.setEngineCapacity(vehicle.getEngineCapacity());
        responseVehicleDTO.setAccidentCount(vehicle.getAccidentCount());
        responseVehicleDTO.setModel(vehicle.getModel());

        return responseVehicleDTO;
    }


    private Vehicle mapToEntity(RequestVehicleDTO requestVehicleDTO){

        Vehicle vehicle = new Vehicle();

        vehicle.setAccidentCount(requestVehicleDTO.getAccidentCount());
        vehicle.setBrand(requestVehicleDTO.getBrand());
        vehicle.setColor(requestVehicleDTO.getColor());
        vehicle.setDescription(requestVehicleDTO.getDescription());
        vehicle.setEngineCapacity(requestVehicleDTO.getEngineCapacity());
        vehicle.setAccidentCount(requestVehicleDTO.getAccidentCount());
        vehicle.setModel(requestVehicleDTO.getModel());

        return vehicle;
    }



    private Vehicle mapToEntity(RequestUpdateVehicleDTO requestUpdateVehicleDTO){

        Vehicle vehicle = new Vehicle();

        vehicle.setAccidentCount(requestUpdateVehicleDTO.getAccidentCount());
        vehicle.setBrand(requestUpdateVehicleDTO.getBrand());
        vehicle.setColor(requestUpdateVehicleDTO.getColor());
        vehicle.setDescription(requestUpdateVehicleDTO.getDescription());
        vehicle.setEngineCapacity(requestUpdateVehicleDTO.getEngineCapacity());
        vehicle.setAccidentCount(requestUpdateVehicleDTO.getAccidentCount());
        vehicle.setModel(requestUpdateVehicleDTO.getModel());

        return vehicle;
    }
}

