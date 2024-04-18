package com.foldouts.foldouts.dao;

import com.foldouts.foldouts.models.Role;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends MongoRepository<Role, Integer> {
    Optional<Role> findByAuthority(String authority);
}
