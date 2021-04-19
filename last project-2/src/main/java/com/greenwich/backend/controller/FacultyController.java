package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.service.FacultyService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/Faculty")
public class FacultyController {
    @Autowired
    private FacultyService facultyService;

    @GetMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Faculty> findById(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<PagingResponse<Faculty>> findAllUserInformation
            (@RequestParam int pageNumber,
             @RequestParam int pageSize,
             @RequestParam String nam) {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.findFaculty(pageNumber, pageSize, nam));
    }

    @GetMapping(value = "/searchByKey")
    public ResponseEntity<PagingResponse<Faculty>> search(
            @RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String nam,
            @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.searchByKey(pageNumber, pageSize, nam, keyword));
    }

    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Faculty> insert(@RequestBody Faculty faculty) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.insert(faculty));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Faculty> edit(@PathVariable("id") ObjectId id,
                                                @RequestBody Faculty faculty) throws ServiceException {
        faculty.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.edit(faculty));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(facultyService.delete(facultyService.findById(id)));
    }

//    @PutMapping(value = "/addTopicToFaculty", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Faculty> addTopicToFaculty(@RequestBody Faculty faculty,
//                                                     @RequestParam String codeTopic ,
//                                                     @RequestParam String nameTopic) throws ServiceException {
//        return ResponseEntity.status(HttpStatus.OK).body(facultyService.addTopicToFaculty(faculty,codeTopic,nameTopic));
//    }


//    @PutMapping(value = "/addTopic", consumes = "application/json", produces = "application/json")
//    public ResponseEntity<Faculty> addTopic(
//            @RequestBody Faculty faculty,
//            @RequestParam String topicId) throws ServiceException {
//        return ResponseEntity.status(HttpStatus.OK).body(facultyService.addTopic(faculty, topicId));
//    }
}
