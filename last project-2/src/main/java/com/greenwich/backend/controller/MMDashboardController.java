package com.greenwich.backend.controller;

import com.greenwich.backend.service.impl.SubmissionService;
import com.greenwich.backend.service.impl.UserInformationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/MMDashboard")
public class MMDashboardController {

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    SubmissionService submissionService;

    @GetMapping(value = "/countSearchByKey")
    public ResponseEntity<?> countUser(@RequestParam String keyword,
                                       @RequestParam String nam){
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.countUser(keyword, nam));
    }

    @GetMapping(value = "/countSearchByKey/submission")
    public ResponseEntity<?> countSubmission(@RequestParam String keyword,@RequestParam String nam,
                                             @RequestParam boolean status){
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.countSubmission(keyword,nam,status));
    }
}
