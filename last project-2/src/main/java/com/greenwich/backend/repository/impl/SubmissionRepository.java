package com.greenwich.backend.repository.impl;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.repository.ISubmissionRepository;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SubmissionRepository implements ISubmissionRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public PagingResponse<Submission> findSubmission(int pageNumber, int pageSize, String nam) {
        Query query = new Query(Criteria.where("nam").is(nam)).with(PageRequest.of(pageNumber - 1, pageSize));
        List<Submission> content = mongoTemplate.find(query, Submission.class);
        long totalElements = mongoTemplate.count(new Query(), Submission.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public PagingResponse<Submission> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        Criteria name = Criteria.where("nameFaculty").regex(searchKey, "i");
        Criteria File = Criteria.where("codeSubmission").regex(searchKey, "i");
        Criteria nameUser = Criteria.where("codeUser").regex(searchKey, "i");
        Criteria topic = Criteria.where("codeTopic").regex(searchKey, "i");
        Criteria recommend = Criteria.where("codeUserRecommend").regex(searchKey,"i");
        Criteria trangThai = Criteria.where("status").is(true);
        Criteria summary = new Criteria().orOperator(name, File, nameUser,topic, trangThai,recommend);

        Query pagingQuery = new Query(summary).with(PageRequest.of(pageNumber - 1, pageSize));

        Query countQuery = new Query(summary);

        List<Submission> content = mongoTemplate.find(pagingQuery, Submission.class);
        long totalElements = mongoTemplate.count(countQuery, Submission.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public PagingResponse<Submission> searchHistory(int pageNumber, int pageSize, String nam, String searchKey, boolean status) {
        Criteria recommend = Criteria.where("codeUserRecommend").regex(searchKey,"i");
        Criteria trangthai = Criteria.where("status").is(true);
        Criteria summary = new Criteria().andOperator(trangthai,recommend);

        Query pagingQuery = new Query(summary).with(PageRequest.of(pageNumber - 1, pageSize));
        Query countQuery = new Query(summary);

        List<Submission> content = mongoTemplate.find(pagingQuery, Submission.class);
        long totalElements = mongoTemplate.count(countQuery, Submission.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public Submission findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, Submission.class);
    }

    @Override
    public Submission insert(Submission submission) {
        return mongoTemplate.insert(submission);
    }

    @Override
    public Submission edit(Submission submission) {
        return mongoTemplate.save(submission);
    }

    @Override
    public boolean delete(Submission submission) {
        return mongoTemplate.remove(submission).getDeletedCount() > 0;
    }

    @Override
    public Submission findByCodeOrName(String codeSubmission, String nameSubmission) {
        Criteria code = Criteria.where("codeSubmission").regex("^" + codeSubmission + "$", "i");
        Criteria name = Criteria.where("name").regex("^" + nameSubmission + "$", "i");
        Criteria summary = new Criteria().orOperator(code, name);
        Query query = new Query(summary);
        return mongoTemplate.findOne(query, Submission.class);
    }

    @Override
    public Submission findByCodeOrNameAndNotId(String codeSubmission, String nameSubmission, ObjectId id) {
        Criteria code = Criteria.where("codeSubmission").regex("^" + codeSubmission + "$", "i");
        Criteria name = Criteria.where("name").regex("^" + nameSubmission + "$", "i");
        Criteria summary = new Criteria().orOperator(code, name);
        Criteria idUser = new Criteria("_id").ne(id);
        Query query = new Query(new Criteria().andOperator(idUser, summary));
        return mongoTemplate.findOne(query, Submission.class);
    }

    @Override
    public Long countSubmisson(String searchKey, String nam, boolean trangThai) {
        Criteria faculty = Criteria.where("nameFaculty").regex(searchKey, "i");
        Criteria user = Criteria.where("codeUser").regex(searchKey, "i");
        Criteria baiTot = Criteria.where("status").is(true);
        Criteria namQ = Criteria.where("nam").is(nam);
        Criteria summary = new Criteria().orOperator(faculty, user, baiTot, namQ);

        Query countQuery = new Query(summary);
//        long elements = mongoTemplate.count(countQuery, User.class);
        return mongoTemplate.count(countQuery, Submission.class);
    }

    @Override
    public Long countSubmissonNotStatus(String searchKey, String nam) {
        Criteria faculty = Criteria.where("nameFaculty").regex(searchKey, "i");
        Criteria user = Criteria.where("codeUser").regex(searchKey, "i");
        Criteria namQ = Criteria.where("nam").is(nam);
        Criteria summary = new Criteria().orOperator(faculty, user, namQ);

        Query countQuery = new Query(summary);
//        long elements = mongoTemplate.count(countQuery, User.class);
        return mongoTemplate.count(countQuery, Submission.class);
    }
}

