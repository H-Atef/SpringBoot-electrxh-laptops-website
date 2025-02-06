package com.electrxh.laptop_website.config;

import com.electrxh.laptop_website.dto.RegistrationDto;
import com.electrxh.laptop_website.model.Role;
import com.electrxh.laptop_website.model.UserEntity;
import com.electrxh.laptop_website.repository.RoleRepo;
import com.electrxh.laptop_website.repository.UserRepo;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;

@Component
@RequiredArgsConstructor
public class DataInitializer {

    private final RoleRepo roleRepository;
    private final PasswordEncoder passwordEncoder;
    private final UserRepo userRepository;

    @Bean
    public void initRoles() {
        if (roleRepository.count() == 0) {
            List<Role> roles = List.of(
                    new Role(1, "Admin", null),
                    new Role(2, "User", null)
            );
            roleRepository.saveAll(roles);
        }
    }

    @Bean
    public void initAdminUser() {
        if (userRepository.count() == 0) {
            UserEntity user = new UserEntity();
            user.setUsername("admin");
            user.setEmail("admin@admin.com");
            user.setPassword(passwordEncoder.encode("admin"));

            Role role = roleRepository.findByName("Admin");

            user.setRoles(Collections.singletonList(role));

            userRepository.save(user);
        }
    }
}
