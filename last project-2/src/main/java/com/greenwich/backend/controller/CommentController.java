package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Comment;
import com.greenwich.backend.service.CommentService;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/Comment")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Comment> findById(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(commentService.findById(id));
    }

    @PostMapping(value = "/insert", consumes ="application/json", produces = "application/json")
    public ResponseEntity<Comment> insert(@RequestBody Comment comment){
        return ResponseEntity.status(HttpStatus.OK).body(commentService.insert(comment));
    }

    @PutMapping(value="/{id}", consumes ="application/json", produces ="application/json")
    public ResponseEntity<Comment> edit(@PathVariable ObjectId id,
                                        @RequestBody Comment comment){
        comment.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(commentService.edit(comment));
    }

    @DeleteMapping(value="/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws ServiceException{
        return ResponseEntity.status(HttpStatus.OK).body(commentService.delete(commentService.findById(id)));
    }
}
