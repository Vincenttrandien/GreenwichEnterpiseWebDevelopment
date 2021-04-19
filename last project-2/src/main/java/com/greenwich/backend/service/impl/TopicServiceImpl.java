package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.exception.NotFoundException;
import com.greenwich.backend.repository.ITopicRepository;
import com.greenwich.backend.service.ITopicService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Service
public class TopicServiceImpl implements ITopicService {

    @Autowired
    ITopicRepository topicRepository;

    @Override
    public PagingResponse<Topic> findTopic(int pageNumber, int pageSize, String nam) {
        return topicRepository.findTopic(pageNumber,pageSize,nam);
    }

    @Override
    public PagingResponse<Topic> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        if (searchKey == null || searchKey.isEmpty()){
            return topicRepository.findTopic(pageNumber , pageSize , nam);
        }
        return topicRepository.searchByKey(pageNumber,pageSize,nam,searchKey);
    }

    @Override
    public Topic findById(String id) throws ServiceException {
        Topic topic = topicRepository.findById(id);
        if (topic == null) {
            throw new ServiceException(NotFoundException.TOPIC_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return topic;
    }

    @Override
    public Topic insert(Topic topic) throws ServiceException {
        Topic existedTopic = topicRepository
                .findByCodeOrName(topic.getCodeTopic(), topic.getName());

        if (existedTopic != null) {
            throw new ServiceException(NotFoundException.TOPIC_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return topicRepository.insert(topic);
    }

    @Override
    public Topic edit(Topic topic) throws ServiceException {
        Topic existedTopic = topicRepository.findByCodeOrNameAndNotId(
                topic.getCodeTopic(), topic.getName(),
                topic.getId());
        if (existedTopic != null) {
            throw new ServiceException(NotFoundException.TOPIC_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return topicRepository.edit(topic);
    }

    @Override
    public Topic store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Topic topic = new Topic(fileName, file.getContentType(),file.getBytes());
        return topicRepository.edit(topic);
    }

    @Override
    public boolean delete(Topic topic) {
        return topicRepository.delete(topic);
    }

}
