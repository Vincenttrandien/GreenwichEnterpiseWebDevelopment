package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.exception.NotFoundException;
import com.greenwich.backend.repository.IFacultyRepository;
import com.greenwich.backend.repository.ITopicRepository;
import com.greenwich.backend.service.FacultyService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class FacultyServiceImpl implements FacultyService {

    @Autowired
    IFacultyRepository facultyRepository;

    @Autowired
    ITopicRepository topicRepository;

    @Override
    public PagingResponse<Faculty> findFaculty(int pageNumber, int pageSize, String nam) {
        return facultyRepository.findFaculty(pageNumber,pageSize,nam);
    }

    @Override
    public PagingResponse<Faculty> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        if (searchKey == null || searchKey.isEmpty()){
            return facultyRepository.findFaculty(pageNumber , pageSize , nam);
        }
        return facultyRepository.searchByKey(pageNumber,pageSize,nam,searchKey);
    }

    @Override
    public Faculty findById(String id) throws ServiceException {
        Faculty userInformation = facultyRepository.findById(id);
        if (userInformation == null) {
            throw new ServiceException(NotFoundException.FACULTY_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return userInformation;
    }

    @Override
    public Faculty insert(Faculty faculty) throws ServiceException {
        Faculty existedFaculty = facultyRepository
                .findByCodeOrName(faculty.getCodeFaculty(), faculty.getName());

        if (existedFaculty != null) {
            throw new ServiceException(NotFoundException.FACULTY_EXISTED.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return facultyRepository.insert(faculty);
    }

    @Override
    public Faculty edit(Faculty faculty) throws ServiceException {
        Faculty existedUser = facultyRepository.findByCodeOrNameAndNotId(
                faculty.getCodeFaculty(), faculty.getName(),
                faculty.getId());
        if (existedUser != null) {
            throw new ServiceException(NotFoundException.FACULTY_EXISTED.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return facultyRepository.edit(faculty);
    }

    @Override
    public boolean delete(Faculty faculty) {
        return facultyRepository.delete(faculty);
    }

//    @Override
//    public Faculty addTopic(Faculty faculty, String topicId) throws ServiceException {
//        Faculty existedUser = facultyRepository.findByCodeOrNameAndNotId(
//                faculty.getCodeFaculty(), faculty.getName(),
//                faculty.getId());
//        if (existedUser != null) {
//            throw new ServiceException(NotFoundException.FACULTY_EXISTED.getDesc(),
//                    HttpStatus.BAD_REQUEST);
//        }
//        Topic topic = topicRepository.findById(topicId);
//        if (topic == null) {
//            throw new ServiceException(NotFoundException.TOPIC_NOT_FOUND.getDesc(),
//                    HttpStatus.BAD_REQUEST);
//        }
//
//        return facultyRepository.addTopic(faculty,topicId);
//    }

//    @Override
//    public Faculty addTopicToFaculty(Faculty faculty, String codeTopic, String nameTopic) throws ServiceException{
//        if (faculty == null){
//            throw new ServiceException(NotFoundException.FACULTY_NOT_FOUND.getDesc(),
//                    HttpStatus.BAD_REQUEST);
//        }
//        return facultyRepository.addTopicToFaculty(faculty,codeTopic,nameTopic);
//    }
}
