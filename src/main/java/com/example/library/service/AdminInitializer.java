package com.example.library.service;

import com.example.library.entity.ProfileEntity;
import com.example.library.enums.ProfileRole;
import com.example.library.enums.ProfileStatus;
import com.example.library.repository.ProfileRepository;
import com.example.library.util.MDUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class AdminInitializer implements CommandLineRunner {

    private final ProfileRepository profileRepository;

    @Override
    public void run(String... args) throws Exception {
        if (profileRepository.findByRole(ProfileRole.ROLE_ADMIN).isEmpty()) {
            ProfileEntity entity = new ProfileEntity();
            entity.setName("admiin");
            entity.setSurname("adminjon");
            entity.setEmail("admin@gmail.com");
            entity.setPassword(MDUtil.encode("1234"));
            entity.setStatus(ProfileStatus.ACTIVE);
            entity.setRole(ProfileRole.ROLE_ADMIN);
            profileRepository.save(entity);
            log.info("admin initialized {}",entity.getName());
        } else {

        }
    }
}
