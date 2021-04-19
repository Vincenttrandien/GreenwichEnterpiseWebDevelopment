package com.greenwich.backend.service;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;



public interface FacultyService {

    PagingResponse<Faculty> findFaculty(int pageNumber, int pageSize , String nam);

    PagingResponse<Faculty> searchByKey(int pageNumber , int pageSize ,String nam ,String searchKey );

    Faculty findById (String id) throws ServiceException;

    Faculty insert (Faculty faculty) throws ServiceException;

    Faculty edit(Faculty faculty) throws ServiceException;

    boolean delete(Faculty faculty);

//    Faculty addTopic(Faculty faculty, String topicId) throws ServiceException;

//    Faculty addTopicToFaculty(Faculty faculty , String codeTopic, String nameTopic ) throws ServiceException;



}
