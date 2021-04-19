package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.User;
import com.greenwich.backend.exception.NotFoundException;
import com.greenwich.backend.repository.UserInformationRepository;
import com.greenwich.backend.service.IUserInformationService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class UserInformationService implements IUserInformationService {

    @Autowired
    UserInformationRepository userInformationRepository;

    @Override
    public PagingResponse<User> findUserInformation(int pageNumber, int pageSize, String nam) {
        return userInformationRepository.findUserInformation(pageNumber, pageSize, nam);
    }

    @Override
    public PagingResponse<User> searchByKey(int pageNumber, int pageSize, String nam, String searchKey) {
        if (searchKey == null || searchKey.isEmpty()){
            return userInformationRepository.findUserInformation(pageNumber , pageSize , nam);
        }
        return userInformationRepository.searchByKey(pageNumber,pageSize,nam,searchKey);
    }

    @Override
    public User findById(String id) throws ServiceException {
        User user = userInformationRepository.findById(id);
        if (user == null) {
            throw new ServiceException(NotFoundException.USER_NOT_FOUND.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return user;
    }

    @Override
    public User insert(User user) throws ServiceException {
        return null;
    }

    @Override
    public User edit(User user) throws ServiceException {
        User existedUser = userInformationRepository.findByCodeOrNameAndNotId(
                user.getCodeUser(), user.getName(),
                user.getId());
        if (existedUser != null) {
            throw new ServiceException(NotFoundException.USER_EXISTED.getDesc(),
                    HttpStatus.BAD_REQUEST);
        }
        return userInformationRepository.edit(user);    }

    @Override
    public boolean delete(User user) {
        return userInformationRepository.delete(user);
    }

    @Override
    public long countUser(String searchKey, String nam) {
        return userInformationRepository.countUser(searchKey, nam);
    }
}

