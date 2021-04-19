package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Faculty;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.service.IUserInformationService;
import com.greenwich.backend.service.impl.SubmissionService;
import com.greenwich.backend.utils.PagingResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/GuestDashboard")
public class GuestDashBoardController {

    @Autowired
    IUserInformationService userInformationService;

    @Autowired
    SubmissionService submissionService;

    @GetMapping(value = "/searchByKey")
    public ResponseEntity<?> countByKey(
            @RequestParam String keyword,
            @RequestParam String nam) {
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.countUser(keyword,nam));
    }

    @GetMapping(value = "/countSearchByKey/submission")
    public ResponseEntity<?> countSubmission(@RequestParam String keyword,@RequestParam String nam,
                                             @RequestParam boolean status){
        return ResponseEntity.status(HttpStatus.OK).body(submissionService.countSubmission(keyword,nam,status));
    }
}
