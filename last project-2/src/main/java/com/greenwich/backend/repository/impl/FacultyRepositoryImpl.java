package com.greenwich.backend.repository.impl;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.entity.User;
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
import java.util.List;

@Repository
public class FacultyRepositoryImpl implements IFacultyRepository {

    @Autowired
    MongoTemplate mongoTemplate;

//    @Autowired
//    ITopicRepository topicRepository;

    @Override
    public PagingResponse<Faculty> findFaculty(int pageNumber, int pageSize, String nam) {
        Query query = new Query (Criteria.where("nam").is(nam)).with(PageRequest.of(pageNumber - 1, pageSize));
        List<Faculty> content = mongoTemplate.find(query , Faculty.class);
        long totalElements = mongoTemplate.count(new Query(), Faculty.class);
        return new PagingResponse<>(content , totalElements);
    }

    @Override
    public PagingResponse<Faculty> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        Criteria name = Criteria.where("name").regex(searchKey, "i");
        Criteria codeFaculty = Criteria.where("codeFaculty").regex(searchKey, "i");
        Criteria summary = new Criteria().orOperator(name, codeFaculty);

        Query pagingQuery = new Query(summary).with(PageRequest.of(pageNumber - 1, pageSize));
        Query countQuery = new Query(summary);

        List<Faculty> content = mongoTemplate.find(pagingQuery, Faculty.class);
        long totalElements = mongoTemplate.count(countQuery, Faculty.class);
        return new PagingResponse<>(content, totalElements);
    }

    @Override
    public Faculty findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id)) ;
        return mongoTemplate.findOne(query, Faculty.class);
    }

    @Override
    public Faculty insert(Faculty faculty) {
        return mongoTemplate.insert(faculty);
    }

    @Override
    public Faculty edit(Faculty faculty) {
        return mongoTemplate.save(faculty);
    }

    @Override
    public boolean delete(Faculty faculty) {
        return mongoTemplate.remove(faculty).getDeletedCount() > 0;
    }

    @Override
    public Faculty findByCodeOrName(String codeFaculty, String name) {
        Criteria code = Criteria.where("codeFaculty").regex("^" + codeFaculty + "$", "i");
        Criteria nameUser = Criteria.where("name").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameUser);
        Query query = new Query(summary);
        return mongoTemplate.findOne(query, Faculty.class);
    }

    @Override
    public Faculty findByCodeOrNameAndNotId(String codeFaculty, String name, ObjectId id) {
        Criteria code = Criteria.where("codeFaculty").regex("^" + codeFaculty + "$", "i");
        Criteria nameUser = Criteria.where("name").regex("^" + name + "$", "i");
        Criteria summary = new Criteria().orOperator(code, nameUser);
        Criteria idUser = new Criteria("_id").ne(id);
        Query query = new Query(new Criteria().andOperator(idUser, summary));
        return mongoTemplate.findOne(query, Faculty.class);
    }


//    @Override
//    public Faculty addTopic(Faculty faculty, String topicId) {
//        faculty.getTopic().add(topicRepository.findById(topicId));
//        topicRepository.findById(topicId).setFaculty(faculty);
//        return mongoTemplate.save(faculty);
//    }

//    @Override
//    public Faculty addTopicToFaculty(Faculty faculty, String codeTopic , String nameTopic) {
//        faculty.setTopic(Arrays.asList(topicRepository.findByCodeOrName(codeTopic, nameTopic)));
//        return mongoTemplate.save(faculty);
//    }
}
