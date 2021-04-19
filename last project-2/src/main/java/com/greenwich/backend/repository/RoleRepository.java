package com.greenwich.backend.repository;

import com.greenwich.backend.entity.ERole;
import com.greenwich.backend.entity.Role;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface RoleRepository extends MongoRepository<Role, String > {
    Optional<Role> findByName(ERole name);
}
