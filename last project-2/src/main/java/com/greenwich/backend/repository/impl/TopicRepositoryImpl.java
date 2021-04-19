package com.greenwich.backend.repository.impl;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.repository.IFacultyRepository;
import com.greenwich.backend.repository.ITopicRepository;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;

@Repository
public class TopicRepositoryImpl implements ITopicRepository {

    @Autowired
    MongoTemplate mongoTemplate;

    @Autowired
    IFacultyRepository facultyRepository;

    @Override
    public PagingResponse<Topic> findTopic(int pageNumber, int pageSize, String nam) {
        Query query = new Query (Criteria.where("nam").is(nam)).with(PageRequest.of(pageNumber - 1, pageSize));
        List<Topic> content = mongoTemplate.find(query , Topic.class);
        long totalElements = mongoTemplate.count(new Query(), Topic.class);
        return new PagingResponse<>(content , totalElements);
    }

    @Override
    public PagingResponse<Topic> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        Criteria name = Criteria.where("name").regex(searchKey, "i");
        Criteria codeTopic = Criteria.where("codeTopic").regex(searchKey, "i");
        Criteria facultyName = Criteria.where("nameFaculty").regex(searchKey, "i");
        Criteria summary = new Criteria().orOperator(name, codeTopic, facultyName);

        Query pagingQuery = new Query(summary).with(PageRequest.of(pageNumber - 1, pageSize));
        Query countQuery = new Query(summary);

        List<Topic> content = mongoTemplate.find(pagingQuery, Topic.class);
        long totalElements = mongoTemplate.count(countQuery, Topic.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public Topic findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id)) ;
        return mongoTemplate.findOne(query, Topic.class);
    }

    @Override
    public Topic insert(Topic topic) {
        return mongoTemplate.insert(topic);
    }

    @Override
    public Topic edit(Topic topic) {
        return mongoTemplate.save(topic);
    }

    @Override
    public boolean delete(Topic topic) {
        return mongoTemplate.remove(topic).getDeletedCount() > 0;
    }

    @Override
    public Topic findByCodeOrName(String codeTopic, String name) {
        Criteria code = Criteria.where("codeTopic").regex("^" + codeTopic + "$", "i");
        Criteria nameTopic = Criteria.where("name").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameTopic);
        Query query = new Query(summary);
        return mongoTemplate.findOne(query, Topic.class);
    }

    @Override
    public Topic findByCodeOrNameAndNotId(String codeTopic, String name, ObjectId id) {
        Criteria code = Criteria.where("codeTopic").regex("^" + codeTopic + "$", "i");
        Criteria nameTopic = Criteria.where("name").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameTopic);
        Criteria idUser = new Criteria("_id").ne(id);
        Query query = new Query(new Criteria().andOperator(idUser, summary));
        return mongoTemplate.findOne(query, Topic.class);
    }
}
