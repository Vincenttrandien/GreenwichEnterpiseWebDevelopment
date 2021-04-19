package com.greenwich.backend.repository;

import com.greenwich.backend.entity.avatar.Photo;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface PhotoRepository extends MongoRepository<Photo, String> {

}
