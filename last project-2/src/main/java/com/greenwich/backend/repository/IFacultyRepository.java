package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;

public interface IFacultyRepository {

    PagingResponse<Faculty> findFaculty(int pageNumber, int pageSize , String nam);

    PagingResponse<Faculty> searchByKey(int pageNumber , int pageSize , String nam ,String searchKey );

    Faculty findById(String id);

    Faculty insert(Faculty faculty);

    Faculty edit(Faculty faculty);

    boolean delete(Faculty faculty);

    Faculty findByCodeOrName(String codeFaculty, String name);

    Faculty findByCodeOrNameAndNotId(String codeFaculty, String name, ObjectId id);

//    Faculty addTopicToFaculty(Faculty faculty , String codeTopic, String nameTopic );

//    Faculty addTopic(Faculty faculty , String topicId);
}
