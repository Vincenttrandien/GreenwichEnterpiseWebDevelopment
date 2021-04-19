package com.greenwich.backend.controller;

import com.greenwich.backend.entity.ERole;
import com.greenwich.backend.entity.Submission;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.service.impl.SubmissionService;
import com.greenwich.backend.service.impl.UserInformationService;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/StudentDashboard")
public class StudentDashboardController {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    SubmissionService submissionService;

//    @GetMapping(value = "/getFileByYears")
//    public ResponseEntity<?> getFileByYears(@RequestBody User user) throws ServiceException {
//        if (){
//
//        }
//        return ResponseEntity.status(HttpStatus.OK).body(submissionService.findById(id));
//    }

    @GetMapping(value = "/countSearchByKey/submission")
    public ResponseEntity<?> countSubmission(@RequestParam String keyword,@RequestParam String nam,
                                             @RequestParam boolean status){
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.countSubmission(keyword,nam,status));
    }
}
