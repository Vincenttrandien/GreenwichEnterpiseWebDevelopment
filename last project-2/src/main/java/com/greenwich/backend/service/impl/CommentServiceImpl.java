package com.greenwich.backend.service.impl;

import com.greenwich.backend.entity.Comment;
import com.greenwich.backend.exception.NotFoundException;
import com.greenwich.backend.repository.ICommentRepository;
import com.greenwich.backend.service.CommentService;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired
    ICommentRepository commentRepository;


    @Override
    public Comment findById(String id) throws ServiceException {
        Comment comment  = commentRepository.findById(id);
        if(comment == null){
            throw new ServiceException(NotFoundException.COMMENT_NOT_FOUND.getDesc(), HttpStatus.BAD_REQUEST);
        }
        return comment;
    }

    @Override
    public Comment insert(Comment comment) {
        return commentRepository.insert(comment);
    }

    @Override
    public Comment edit(Comment comment){
        return commentRepository.edit(comment);
    }

    @Override
    public boolean delete(Comment comment){
        return commentRepository.delete(comment);
    }
}
