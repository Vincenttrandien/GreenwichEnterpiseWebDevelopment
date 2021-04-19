package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.utils.PagingResponse;
import org.bson.types.ObjectId;

public interface UserInformationRepository {
    PagingResponse<User> findUserInformation(int pageNumber, int pageSize , String nam);

    PagingResponse<User> searchByKey(int pageNumber , int pageSize , String nam ,String searchKey );

    User findById (String id);

//    User insert(User user);

    User edit(User user);

    boolean delete(User user);

    User findByCodeOrName(String codeUser, String name);

    User findByCodeOrNameAndNotId(String codeUser, String name, ObjectId id);

    User changePassword(User user);

    Long countUser(String searchKey , String nam);
}


