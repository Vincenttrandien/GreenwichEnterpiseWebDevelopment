package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.response.MessageResponse;
import com.greenwich.backend.service.ITopicService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/Topic")
public class TopicController {
    @Autowired
    private ITopicService topicService;

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> findById(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.findById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<PagingResponse<Topic>> findAllUserInformation
            (@RequestParam int pageNumber,
             @RequestParam int pageSize,
             @RequestParam String nam) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.findTopic(pageNumber, pageSize, nam));
    }

    @GetMapping(value = "/searchByKey")
    public ResponseEntity<PagingResponse<Topic>> search(
            @RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String nam,
            @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.searchByKey(pageNumber, pageSize, nam, keyword));
    }

    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> insert(@RequestBody Topic topic) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.insert(topic));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Topic> edit(@PathVariable("id") ObjectId id,
                                        @RequestBody Topic topic) throws ServiceException {
        topic.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(topicService.edit(topic));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(topicService.delete(topicService.findById(id)));
    }

    @PutMapping(value ="/upload/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable("id") String id,
                                        @RequestParam("file") MultipartFile files) throws ServiceException, IOException {

        Topic topic = topicService.findById(id);
        topic.setNameFile(files.getOriginalFilename());
        topic.setData(files.getBytes());
        topic.setType(files.getContentType());

        String message = "";

        if (topic != null) {
            return ResponseEntity.status(HttpStatus.OK).body(topicService.edit(topic));
        } else
            message = "Upload fail";
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) throws ServiceException{
        Topic fileDB = topicService.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getNameFile() + "\"")
                .body(fileDB.getData());
    }
}
