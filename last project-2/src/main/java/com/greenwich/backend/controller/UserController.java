package com.greenwich.backend.controller;

import com.greenwich.backend.entity.Role;
import com.greenwich.backend.entity.Topic;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.repository.RoleRepository;
import com.greenwich.backend.service.IUserInformationService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@CrossOrigin
@RestController
@RequestMapping("/greenwich/auth/UserInformation")
public class UserController {

    @Autowired
    private IUserInformationService userInformationService;

    @Autowired
    RoleRepository roleRepository;

    @GetMapping(value = "/{id}")
    public ResponseEntity<User> findById(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.findById(id));
    }

    @GetMapping(value = "/getAll")
    public ResponseEntity<PagingResponse<User>> findAllUserInformation
            (@RequestParam int pageNumber,
             @RequestParam int pageSize,
             @RequestParam String nam) {
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.findUserInformation(pageNumber, pageSize, nam));
    }

    @GetMapping(value = "/searchByKey")
    public ResponseEntity<PagingResponse<User>> search(
            @RequestParam int pageNumber,
            @RequestParam int pageSize, @RequestParam String nam,
            @RequestParam String keyword) {
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.searchByKey(pageNumber, pageSize, nam, keyword));
    }

    @PutMapping(value = "/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<User> edit(@PathVariable("id") ObjectId id,
                                                @RequestBody User user) throws ServiceException {
        user.setId(id);
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.edit(user));
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<Boolean> remove(@PathVariable("id") String id) throws ServiceException {
        return ResponseEntity.status(HttpStatus.OK).body(userInformationService.delete(userInformationService.findById(id)));
    }


//    @GetMapping("/api/admin/user-number")
//    public ResponseEntity<?> numberOfUsers(){
//        Long number = userService.numberOfUsers();
//        StringResponse response = new StringResponse();
//        response.setResponse(number.toString());
//        //to return it, we will use String Response because long is not a suitable response for rest api
//        return new ResponseEntity<>(response, HttpStatus.OK);
//    }
}
