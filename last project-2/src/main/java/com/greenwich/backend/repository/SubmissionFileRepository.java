package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Submission;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubmissionFileRepository extends MongoRepository<Submission, String> {
}
