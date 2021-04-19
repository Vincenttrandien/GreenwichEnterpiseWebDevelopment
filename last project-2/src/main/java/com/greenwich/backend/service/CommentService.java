package com.greenwich.backend.service;

import com.greenwich.backend.entity.Comment;
import com.greenwich.backend.utils.ServiceException;

public interface CommentService {
    Comment findById (String id) throws ServiceException;

    Comment insert (Comment comment);

    Comment edit(Comment comment);

    boolean delete(Comment comment);
}
