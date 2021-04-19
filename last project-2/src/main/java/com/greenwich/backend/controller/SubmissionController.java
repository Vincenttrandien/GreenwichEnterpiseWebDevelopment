package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.response.FileResponse;
import com.greenwich.backend.response.MessageResponse;
import com.greenwich.backend.service.ISubmissionService;
import com.greenwich.backend.service.SubmissionStorageService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/Submission")
public class SubmissionController {
    @Autowired
    private ISubmissionService submissionService;

    @Autowired
    private SubmissionStorageService submissionStorageService;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Submission> findById(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.findById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<PagingResponse<Submission>> findAllSubmission
            (@RequestParam int pageNumber,
             @RequestParam int pageSize,
             @RequestParam String nam) {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.findSubmission(pageNumber, pageSize, nam));
    }

    @GetMapping(value = "/searchByKey")
    public ResponseEntity<PagingResponse<Submission>> search(
            @RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String nam,
            @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.searchByKey(pageNumber, pageSize, nam, keyword));
    }

    @GetMapping(value = "/searchHistory")
    public ResponseEntity<PagingResponse<Submission>> searchHistory(
            @RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String nam,
            @RequestParam String keyword,
            @RequestParam boolean status) {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.searchHistory(pageNumber, pageSize, nam, keyword, status));
    }

    @PostMapping(value = "/insert", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Submission> insert(@RequestBody Submission submission) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.insert(submission));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Submission> edit(@PathVariable("id") ObjectId id,
                                           @RequestBody Submission submission) throws ServiceException {
//        submission.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.edit(submission));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.delete(submissionService.findById(id)));
    }

    @PutMapping(value ="/upload/{id}")
    public ResponseEntity<?> uploadFile(@PathVariable("id") String id,
                                        @RequestParam("file") MultipartFile file) throws ServiceException, IOException {

        Submission submission = submissionService.findById(id);
        submission.setName(file.getOriginalFilename());
        submission.setData(file.getBytes());
        submission.setType(file.getContentType());

        String message = "";

        if(submission != null){
            return ResponseEntity.status(HttpStatus.OK).body(submissionService.edit(submission));
        }
        else
            message = "Upload fail";
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new MessageResponse(message));

//        try {
//            submissionService.store(file);
//
//            message = "Uploaded the file successfully: " + file.getOriginalFilename();
//            return ResponseEntity.status(HttpStatus.OK).body(new MessageResponse(message));
//        } catch (Exception e) {
//            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
//            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new MessageResponse(message));
//        }
    }


    @GetMapping("/files")
    public ResponseEntity<List<FileResponse>> getListFiles() {
        List<FileResponse> files = submissionStorageService.getAllFiles().map(dbFile -> {
            String fileDownloadUri = ServletUriComponentsBuilder
                    .fromCurrentContextPath()
                    .path("/files/")
                    .path(dbFile.getId().toString())
                    .toUriString();

            return new FileResponse(
                    dbFile.getId().toString(),
                    dbFile.getName(),
                    fileDownloadUri,
                    dbFile.getType(),
                    dbFile.getCodeSubmission(),
                    dbFile.getComment(),
                    dbFile.getDateSubmit(),
                    dbFile.isStatus(),
                    dbFile.getCodeUser(),
                    dbFile.getNameFaculty(),
                    dbFile.getCodeTopic(),
                    dbFile.getCodeUserRecommend(),
                    dbFile.getNam(),
                    dbFile.getData().length
                    );
        }).collect(Collectors.toList());

        return ResponseEntity.status(HttpStatus.OK).body(files);
    }

    @GetMapping("/files/{id}")
    public ResponseEntity<byte[]> getFile(@PathVariable String id) throws ServiceException{
        Submission fileDB = submissionService.findById(id);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + fileDB.getName() + "\"")
                .body(fileDB.getData());
    }
}
