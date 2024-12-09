package com.example.api_grupo_sura.service;

import com.example.api_grupo_sura.dto.userDto.ResponseUserDTO;
import com.example.api_grupo_sura.entity.User;

import java.util.List;

public interface IUserService {

    User createUser(User user);
    List<ResponseUserDTO> findAllUser();
    ResponseUserDTO findUserById(Long id);
    User updateUser(User user);
    void deleteUserById(Long id);

}
