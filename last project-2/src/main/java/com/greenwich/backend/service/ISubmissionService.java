package com.greenwich.backend.service;

import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Path;
import java.util.Optional;
import java.util.stream.Stream;

public interface ISubmissionService {
    PagingResponse<Submission> findSubmission(int pageNumber, int pageSize , String nam);

    PagingResponse<Submission> searchByKey(int pageNumber , int pageSize ,String nam ,String searchKey );

    Submission findById (String id) throws ServiceException;

    Submission insert (Submission submission) throws ServiceException;

    Submission edit(Submission submission) throws ServiceException;

    boolean delete(Submission submission);

    Long countSubmissionHasStatus(String searchKey);

     Submission store(MultipartFile file) throws IOException;

     long countSubmission(String searchKey, String nam, boolean trangThai);

     long countSubmissonNotStatus(String searchKey, String nam);

    PagingResponse<Submission> searchHistory(int pageNumber, int pageSize, String nam , String searchKey, boolean status);


}
