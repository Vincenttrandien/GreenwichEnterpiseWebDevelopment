package com.greenwich.backend.service;

import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.repository.SubmissionFileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.stream.Stream;

@Service
public class SubmissionStorageService {

    @Autowired
    SubmissionFileRepository submissionFileRepository;
    public Stream<Submission> getAllFiles() {
        return submissionFileRepository.findAll().stream();
    }
}
