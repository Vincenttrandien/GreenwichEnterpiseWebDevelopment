package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.exception.NotFoundException;
import com.greenwich.backend.repository.ISubmissionRepository;
import com.greenwich.backend.repository.SubmissionFileRepository;
import com.greenwich.backend.service.ISubmissionService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Stream;

@Service
public class SubmissionService implements ISubmissionService {

    @Autowired
    ISubmissionRepository submissionRepository;

    @Autowired
    SubmissionFileRepository submissionFileRepository;

    @Override
    public PagingResponse<Submission> findSubmission(int pageNumber, int pageSize, String nam) {
        return submissionRepository.findSubmission(pageNumber,pageSize,nam);
    }

    @Override
    public PagingResponse<Submission> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        if (searchKey == null || searchKey.isEmpty()){
            return submissionRepository.findSubmission(pageNumber , pageSize , nam);
        }
        return submissionRepository.searchByKey(pageNumber,pageSize,nam,searchKey);
    }

    @Override
    public Submission findById(String id) throws ServiceException {
        Submission submission = submissionRepository.findById(id);
        if (submission == null) {
            throw new ServiceException(NotFoundException.SUBMISSION_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return submission;
    }

    @Override
    public Submission insert(Submission submission) throws ServiceException {
//        Submission existedSubmission = submissionRepository
//                .findByCodeOrName(submission.getCodeSubmission(), submission.getName());
//
//        if (existedSubmission != null) {
//            throw new ServiceException(NotFoundException.FACULTY_EXISTED.getDesc(),
//                    HttpStatus.BAD_REQUEST);
//        }
        return submissionRepository.insert(submission);
    }

    @Override
    public Submission edit(Submission submission) throws ServiceException {
//        Submission existedUser = submissionRepository.findByCodeOrNameAndNotId(
//                submission.getCodeSubmission(), submission.getName(),
//                submission.getId());
//        if (existedUser != null) {
//            throw new ServiceException(NotFoundException.FACULTY_EXISTED.getDesc(),
//                    HttpStatus.BAD_REQUEST);
//        }
        return submissionRepository.edit(submission);
    }

    @Override
    public boolean delete(Submission submission) {
        return submissionRepository.delete(submission);
    }

    @Override
    public Long countSubmissionHasStatus(String searchKey) {
        return null;
    }

    @Override
    public Submission store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Submission submission = new Submission(fileName, file.getContentType(),file.getBytes());

        return submissionRepository.edit(submission);
    }

    @Override
    public long countSubmission(String searchKey, String nam, boolean trangThai) {
        if(trangThai == false){
            return  submissionRepository.countSubmissonNotStatus(searchKey, nam);
        }
        return submissionRepository.countSubmisson(searchKey,nam,trangThai);
    }

    @Override
    public long countSubmissonNotStatus(String searchKey, String nam) {
        return  submissionRepository.countSubmissonNotStatus(searchKey, nam);
    }

    @Override
    public PagingResponse<Submission> searchHistory(int pageNumber, int pageSize, String nam, String searchKey, boolean status) {
        return submissionRepository.searchHistory(pageNumber,pageSize,nam,searchKey,status);
    }


    public Stream<Submission> getAllFiles() {
        return submissionFileRepository.findAll().stream();
    }
}

