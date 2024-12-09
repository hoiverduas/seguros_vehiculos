package com.example.api_grupo_sura.service.impl;

import com.example.api_grupo_sura.dto.userDto.ResponseUserDTO;
import com.example.api_grupo_sura.dto.userDto.ResponseUserVehicleDTO;
import com.example.api_grupo_sura.dto.vehicleDto.ResponseVehicleDTO;
import com.example.api_grupo_sura.entity.User;
import com.example.api_grupo_sura.entity.Vehicle;
import com.example.api_grupo_sura.repository.IUserRepository;
import com.example.api_grupo_sura.service.IUserService;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserService implements IUserService {

    private final IUserRepository userRepository;

    public UserService(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }



    @Override
    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    @Override
    public List<ResponseUserDTO> findAllUser() {
        List<User> users = userRepository.findAllWithVehicles();

        return users.stream()
                .map(this::mapToResponseUserDTO)
                .collect(Collectors.toList());
    }

    @Override
    public ResponseUserDTO findUserById(Long id) {
        User user = userRepository.findByIdWithVehicles(id)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return mapToResponseUserDTO(user);
    }

    @Override
    public User updateUser(User user) {

        Optional<User> userOptional = userRepository.findById(user.getId());

        if (userOptional.isPresent()){

            User userExists = userOptional.get();

            userExists.setId(user.getId());
            userExists.setName(user.getName());
            userExists.setEmail(user.getEmail());
            userExists.setCedula(user.getCedula());
            userExists.setPhone(user.getPhone());
            userExists.setBirthDate(LocalDate.now());

            return this.userRepository.save(userExists);


        }else {
            throw new RuntimeException("no found");
        }

    }

    @Override
    public void deleteUserById(Long id) {
        findUserById(id);
        this.userRepository.deleteById(id);
    }


    private ResponseUserDTO mapToResponseUserDTO(User user) {
        ResponseUserDTO responseUserDTO = new ResponseUserDTO();

        responseUserDTO.setId(user.getId());
        responseUserDTO.setCedula(user.getCedula());
        responseUserDTO.setName(user.getName());
        responseUserDTO.setPhone(user.getPhone());
        responseUserDTO.setEmail(user.getEmail());
        responseUserDTO.setAddress(user.getAddress());
        responseUserDTO.setBirthDate(user.getBirthDate());
        responseUserDTO.setVehicles(user.getVehicles().stream()
                .map(this::mapToResponseVehicleDTO)
                .collect(Collectors.toList()));

        return responseUserDTO;
    }

    private ResponseUserVehicleDTO mapToResponseVehicleDTO(Vehicle vehicle) {

        ResponseUserVehicleDTO responseUserVehicleDTO = new ResponseUserVehicleDTO();

        responseUserVehicleDTO.setId(vehicle.getId());
        responseUserVehicleDTO.setBrand(vehicle.getBrand());
        responseUserVehicleDTO.setModel(vehicle.getModel());
        responseUserVehicleDTO.setColor(vehicle.getColor());
        responseUserVehicleDTO.setDescription(vehicle.getDescription());
        responseUserVehicleDTO.setAccidentCount(vehicle.getAccidentCount());
        responseUserVehicleDTO.setEngineCapacity(vehicle.getEngineCapacity());

        return responseUserVehicleDTO;
    }





}
