package com.example.springsercurityclass35.config;


import com.example.springsercurityclass35.entities.Role;
import com.example.springsercurityclass35.entities.User;
import com.example.springsercurityclass35.entities.enums.RoleType;
import com.example.springsercurityclass35.repo.RoleRepository;
import com.example.springsercurityclass35.repo.UserRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class DataSeeder {
    @Bean
    public CommandLineRunner commandLineRunner(
            UserRepository userRepository,
            RoleRepository roleRepository,
            PasswordEncoder passwordEncoder
    ){
        return args ->{
            Role userRole = roleRepository.findByName(RoleType.USER);
            Role adminRole = roleRepository.findByName(RoleType.ADMIN);

            if(!userRepository.existsByUsername("admin")){
                User user = new User();
                user.setUsername("admin");
                user.setPassword(passwordEncoder.encode("admin"));
                user.setEmail("admin@gmail.com");
                user.getRoles().add(userRole);
                user.getRoles().add(adminRole);

                userRepository.save(user);
            }
        };
    }
}
