package com.greenwich.backend.repository.impl;

import com.greenwich.backend.entity.Comment;
import com.greenwich.backend.repository.ICommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CommentRepositoryImpl implements ICommentRepository {
    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public Comment findById(String id) {
        Query query = new Query().addCriteria(Criteria.where("_id").is(id)) ;
        return mongoTemplate.findOne(query, Comment.class);
    }

    @Override
    public Comment insert(Comment comment) {
        return mongoTemplate.insert(comment);
    }

    @Override
    public Comment edit(Comment comment) {
        return mongoTemplate.save(comment);
    }

    @Override
    public boolean delete(Comment comment) {
        return mongoTemplate.remove(comment).getDeletedCount() > 0;
    }
}
