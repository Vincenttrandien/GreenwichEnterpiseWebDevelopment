package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.service.FacultyService;
import com.greenwich.backend.service.impl.SubmissionService;
import com.greenwich.backend.service.impl.UserInformationService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.rmi.server.ServerCloneException;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/MCDashboard")
public class MCDashboardController{

    @Autowired
    UserInformationService userInformationService;

    @Autowired
    SubmissionService submissionService;

    @GetMapping(value = "/countSearchByKey")
    public ResponseEntity<?> count(@RequestParam String keyword,@RequestParam String nam){
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.countUser(keyword,nam));
    }

    @GetMapping(value = "/countSearchByKey/submission")
    public ResponseEntity<?> countSubmission(@RequestParam String keyword,@RequestParam String nam,
                                             @RequestParam boolean status){
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.countSubmission(keyword,nam,status));
    }
}
