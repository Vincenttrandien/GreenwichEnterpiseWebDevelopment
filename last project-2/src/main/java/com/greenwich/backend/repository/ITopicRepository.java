package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;

public interface ITopicRepository {
    PagingResponse<Topic> findTopic(int pageNumber, int pageSize , String nam);

    PagingResponse<Topic> searchByKey(int pageNumber , int pageSize , String nam ,String searchKey );

    Topic findById(String id);

    Topic insert(Topic topic);

    Topic edit(Topic topic);

    boolean delete(Topic topic);

    Topic findByCodeOrName(String codeTopic, String nameTopic);

    Topic findByCodeOrNameAndNotId(String codeUser, String name, ObjectId id);
}
