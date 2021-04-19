package com.greenwich.backend.service;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface ITopicService {
    PagingResponse<Topic> findTopic(int pageNumber, int pageSize , String nam);

    PagingResponse<Topic> searchByKey(int pageNumber , int pageSize ,String nam ,String searchKey );

    Topic findById (String id) throws ServiceException;

    Topic insert (Topic topic) throws ServiceException;

    Topic edit(Topic topic) throws ServiceException;

    Topic store(MultipartFile file) throws IOException;
    boolean delete(Topic topic);

}
