package com.main.app.config;


import com.main.app.models.Role;
import com.main.app.models.User;
import com.main.app.repo.RoleRepository;
import com.main.app.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
public class DataLoader implements ApplicationRunner {

    final
    UserRepository userRepository;

    final
    RoleRepository roleRepository;

    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

    @Autowired
    public DataLoader(UserRepository userRepository, RoleRepository roleRepository) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
    }

    public void run(ApplicationArguments args) {
        roleRepository.save(new Role(1L, "ROLE_USER"));
        roleRepository.save(new Role(2L, "ROLE_EDITOR"));
        roleRepository.save(new Role(3L, "ROLE_ADMIN"));
        User user = new User();
        user.setUsername("admin");
        user.setPassword(encoder.encode("admin1234"));
        user.setRoles(Collections.singleton(new Role(3L, "ROLE_ADMIN")));
        userRepository.save(user);
        User user2 = new User();
        user2.setUsername("Manager");
        user2.setPassword(encoder.encode("manager1234"));
        user2.setRoles(Collections.singleton(new Role(2L, "ROLE_EDITOR")));
        userRepository.save(user2);
        User user3 = new User();
        user3.setUsername("Nikita");
        user3.setPassword(encoder.encode("user1234"));
        user3.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user3);
        User user4 = new User();
        user4.setUsername("Artem");
        user4.setPassword(encoder.encode("user1234"));
        user4.setRoles(Collections.singleton(new Role(1L, "ROLE_USER")));
        userRepository.save(user4);
    }
}