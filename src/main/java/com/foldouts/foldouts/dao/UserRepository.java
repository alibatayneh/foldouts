package com.foldouts.foldouts.dao;

import com.foldouts.foldouts.models.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends MongoRepository<ApplicationUser, Integer> {
    Optional<ApplicationUser> findByUsername(String username);
}
