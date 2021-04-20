package com.greenwich.backend;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
public class EnterpisewebApplication{

    public static void main(String[] args) {
        SpringApplication.run(EnterpisewebApplication.class, args);
    }

//    @Autowired
//    PasswordEncoder passwordEncoder;
//
//    @Autowired
//    UserRepository userRepository;
//
//    @Autowired
//    RoleRepository roleRepository;

//    @Override
//    public void run(String... args) throws Exception {
//
//        User user = new User();
//        user.setCodeUser("Admin1");
//        user.setName("admin");
//        user.setEmail("admin@gmail.com");
//        user.setDob("1-1-2000");
//        user.setAddress("VietNam");
//        user.setPhoneNumber("+84");
//        user.setNam("2020");
//        user.setUsername("admin");
//        user.setPassword("admin1");
//        user.setRetypePassword("admin1");
//        user.setFacultyName("Admin");
//
//        Set<Role> roles = new HashSet<>();
//
//        Role userRole = roleRepository.findByName(ERole.ROLE_ADMIN)
//                .orElseThrow(() -> new RuntimeException("Error: Role is not found."));
//        roles.add(userRole);
//
//        user.setRoles(roles);
//
//        userRepository.save(user);
//    }
}
