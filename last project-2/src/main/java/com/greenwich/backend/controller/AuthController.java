package com.greenwich.backend.controller;

import com.greenwich.backend.entity.ERole;
import com.greenwich.backend.entity.Role;
import com.greenwich.backend.entity.User;
import com.greenwich.backend.jwt.JwtUtils;
import com.greenwich.backend.repository.RoleRepository;
import com.greenwich.backend.repository.UserRepository;
import com.greenwich.backend.request.LoginRequest;
import com.greenwich.backend.request.SignupRequest;
import com.greenwich.backend.response.JwtResponse;
import com.greenwich.backend.response.MessageResponse;
import com.greenwich.backend.service.UserDetailsImpl;
import com.greenwich.backend.service.impl.NotificationService;
import com.greenwich.backend.utils.PagingResponse;
import com.greenwich.backend.utils.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.mail.MailException;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/greenwich/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    NotificationService notificationService;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {


        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getId().toHexString(),
                userDetails.getCodeUser(),
                userDetails.getName(),
                userDetails.getEmail(),
                userDetails.getDob(),
                userDetails.getAddress(),
                userDetails.getPhoneNumber(),
                userDetails.getNam(),
                userDetails.getUsername(),
                userDetails.getFacultyName(),
                roles));
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Email is already in use!"));
        }

        if (userRepository.existsByCodeUser(signUpRequest.getCodeUser())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: CodeUser is already in use!"));
        }

        // Create new user's account
        User user = new User(
                signUpRequest.getCodeUser(),
                signUpRequest.getName(),
                signUpRequest.getEmail(),
                signUpRequest.getDob(),
                signUpRequest.getAddress(),
                signUpRequest.getPhoneNumber(),
                signUpRequest.getNam(),
                signUpRequest.getFacultyName(),
                signUpRequest.getUsername(),
                encoder.encode(signUpRequest.getPassword()),
                encoder.encode(signUpRequest.getRetypePassword()));

        Set<String> strRoles = signUpRequest.getRoles();
        Set<Role> roles = new HashSet<>();

        if (strRoles == null) {
            Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
                    .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "Admin":
                        Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(adminRole);

                        break;
                    case "Marketing Manager":
                        Role modRole = roleRepository.findByName(ERole.ROLE_MM)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(modRole);
                        break;
                    case "Marketing Coordinator":
                        Role mcRole = roleRepository.findByName(ERole.ROLE_MC)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(mcRole);
                        break;
                    case "Student":
                        Role studentRole = roleRepository.findByName(ERole.ROLE_STUDENT)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(studentRole);

                        break;
                    default:
                        Role userRole = roleRepository.findByName(ERole.ROLE_GUEST)
                                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
                        roles.add(userRole);
                }
            });
        }

        user.setRoles(roles);
        userRepository.save(user);
        notificationService.sendNotification(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }

}
