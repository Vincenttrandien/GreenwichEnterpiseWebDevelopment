package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;

import java.util.List;

public interface ISubmissionRepository {
    PagingResponse<Submission> findSubmission(int pageNumber, int pageSize , String nam);

    PagingResponse<Submission> searchByKey(int pageNumber , int pageSize , String nam ,String searchKey );

    PagingResponse<Submission> searchHistory(int pageNumber, int pageSize, String nam , String searchKey, boolean status);

    Submission findById(String id);

    Submission insert(Submission submission);

    Submission edit(Submission submission);

    boolean delete(Submission submission);

    Submission findByCodeOrName(String codeSubmission, String name);

    Submission findByCodeOrNameAndNotId(String codeSubmission, String name, ObjectId id);

    Long countSubmisson(String searchKey, String nam, boolean trangThai);

    Long countSubmissonNotStatus(String searchKey, String nam);

}
