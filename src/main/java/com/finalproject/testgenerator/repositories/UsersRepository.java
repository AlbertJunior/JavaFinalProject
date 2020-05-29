package com.finalproject.testgenerator.repositories;

import com.finalproject.testgenerator.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsersRepository extends JpaRepository<User, Integer> {
    User findByUsername(String username);
}