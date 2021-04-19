package com.greenwich.backend.repository.impl;

import com.greenwich.backend.entity.User;
import com.greenwich.backend.repository.IFacultyRepository;
import com.greenwich.backend.repository.UserInformationRepository;
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
public class UserInformationRepositoryImpl implements UserInformationRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    IFacultyRepository facultyRepository;

    @Override
    public PagingResponse<User> findUserInformation(int pageNumber, int pageSize, String nam) {
        Query query = new Query (Criteria.where("nam").is(nam)).with(PageRequest.of(pageNumber - 1, pageSize));
        List<User> content = mongoTemplate.find(query , User.class);
        long totalElements = mongoTemplate.count(new Query(), User.class);
        return new PagingResponse<>(content , totalElements);
    }

    @Override
    public PagingResponse<User> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        Criteria name = Criteria.where("name").regex(searchKey, "i");
        Criteria email = Criteria.where("email").regex(searchKey, "i");
        Criteria phoneNumber = Criteria.where("phoneNumber").regex(searchKey,"i");
        Criteria code = Criteria.where("codeUser").regex(searchKey, "i");
        Criteria summary = new Criteria().orOperator(name, email, phoneNumber,code);

        Query pagingQuery = new Query(summary).with(PageRequest.of(pageNumber - 1, pageSize));
        Query countQuery = new Query(summary);

        List<User> content = mongoTemplate.find(pagingQuery, User.class);
        long totalElements = mongoTemplate.count(countQuery, User.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public User findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id));
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User edit(User user) {
        return mongoTemplate.save(user);
    }

    @Override
    public boolean delete(User user) {
        return mongoTemplate.remove(user).getDeletedCount() > 0;
    }

    @Override
    public User findByCodeOrName(String codeUser, String name) {
        Criteria code = Criteria.where("codeUser").regex("^" + codeUser + "$", "i");
        Criteria nameUser = Criteria.where("name").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameUser);
        Query query = new Query(summary);
        return mongoTemplate.findOne(query, User.class);
    }

    @Override
    public User findByCodeOrNameAndNotId(String codeUser, String name, ObjectId id) {
        Criteria code = Criteria.where("codeUser").regex("^" + codeUser + "$", "i");
        Criteria nameUser = Criteria.where("nameUser").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameUser);
        Criteria idUser = new Criteria("_id").ne(id);
        Query query = new Query(new Criteria().andOperator(idUser, summary));
        return mongoTemplate.findOne(query, User.class);
    }


    @Override
    public User changePassword(User user) {
        String newPassWord = new String();

        return null;
    }

    @Override
    public Long countUser(String searchKey, String nam) {
        Criteria name = Criteria.where("facultyName").regex(searchKey, "i");
        Criteria email = Criteria.where("codeUser").regex(searchKey, "i");
        Criteria phoneNumber = Criteria.where("phoneNumber").regex(searchKey,"i");
        Criteria year = Criteria.where("nam").is(nam);
        Criteria summary = new Criteria().orOperator(name, email, phoneNumber, year);

        Query countQuery = new Query(summary);
//        long elements = mongoTemplate.count(countQuery, User.class);
        return mongoTemplate.count(countQuery, User.class);
    }
}
