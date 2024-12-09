package com.example.api_grupo_sura.repository;


import com.example.api_grupo_sura.entity.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface IUserRepository extends CrudRepository<User,Long> {

    @Query("SELECT u FROM User u LEFT JOIN FETCH u.vehicles WHERE u.id = :id")
    Optional<User> findByIdWithVehicles(@Param("id") Long id);
    @Query("SELECT u FROM User u LEFT JOIN FETCH u.vehicles")
    List<User> findAllWithVehicles();
}
