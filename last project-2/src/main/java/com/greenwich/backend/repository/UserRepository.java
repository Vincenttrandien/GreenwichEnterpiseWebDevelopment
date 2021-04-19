package com.greenwich.backend.repository;

import com.greenwich.backend.entity.User;
import com.greenwich.backend.utils.PagingResponse;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {

    Optional<User> findByUsername(String username);

    Boolean existsByCodeUser(String codeUser);

    Boolean existsByUsername(String username);

    Boolean existsByEmail(String email);

}
