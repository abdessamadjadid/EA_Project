package edu.miu.cs.cs544.EAProject.config;

import edu.miu.cs.cs544.EAProject.domain.Admin;
import edu.miu.cs.cs544.EAProject.domain.User;
import edu.miu.cs.cs544.EAProject.repository.UserRepository;
import edu.miu.cs.cs544.EAProject.service.SecurityService;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;

@Lazy
@RequiredArgsConstructor
@Configuration
public class DataFeeder implements ApplicationRunner {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) {

        /* if (userRepository.findByUsername("admin").isEmpty()) {

            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRoles(Collections.singletonList(new Admin()));

            userRepository.save(admin);
        }*/
    }
}
