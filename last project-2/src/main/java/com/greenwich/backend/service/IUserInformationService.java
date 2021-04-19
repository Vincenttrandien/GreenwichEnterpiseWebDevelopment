package com.greenwich.backend.service;

import com.greenwich.backend.entity.User;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;

public interface IUserInformationService {
    PagingResponse<User> findUserInformation(int pageNumber, int pageSize , String nam);

    PagingResponse<User> searchByKey(int pageNumber , int pageSize , String nam , String searchKey );

    User findById (String id) throws ServiceException;

    User insert(User user) throws ServiceException;

    User edit(User user) throws ServiceException;

    boolean delete(User user);

    long countUser(String searchKey, String nam);
}
