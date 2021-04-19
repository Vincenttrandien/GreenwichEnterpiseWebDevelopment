package com.greenwich.backend.repository;

import com.greenwich.backend.entity.Comment;

public interface ICommentRepository {

    Comment findById(String id);

    Comment insert(Comment comment);

    Comment edit(Comment comment);

    boolean delete(Comment comment);
}
