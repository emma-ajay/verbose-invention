package com.example.PLOT.SELECTION.FORM;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserRepository extends JpaRepository<User, Long> {

    Boolean existsByEmail(String email);

    @Query("SELECT u FROM User u ")
    List<User> getAllUsers();
    
}
