package com.example.library.service;

import com.example.library.dto.AuthDTO;
import com.example.library.dto.JwtDTO;
import com.example.library.dto.profile.ProfileDTO;
import com.example.library.dto.RegistrationDTO;
import com.example.library.entity.ProfileEntity;
import com.example.library.enums.ProfileRole;
import com.example.library.enums.ProfileStatus;
import com.example.library.exp.AppBadException;
import com.example.library.repository.ProfileRepository;
import com.example.library.util.JWTUtil;
import com.example.library.util.MDUtil;
import io.jsonwebtoken.JwtException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Optional;
@Slf4j
@Service
public class AuthService {
    @Autowired
    private MailSenderService mailSenderService;
    @Autowired
    private ProfileRepository profileRepository;

    public Boolean registration(RegistrationDTO dto) {
        Optional<ProfileEntity> optional = profileRepository.findByEmail(dto.getEmail());
        if (optional.isPresent()) {
            if (optional.get().getStatus().equals(ProfileStatus.REGISTRATION)) {
                profileRepository.delete(optional.get());
            } else {
                throw new AppBadException("email exists");
            }
        }
        ProfileEntity entity = new ProfileEntity();
        entity.setName(dto.getName());
        entity.setSurname(dto.getSurname());
        entity.setEmail(dto.getEmail());
        entity.setPassword(MDUtil.encode(dto.getPassword()));
        entity.setStatus(ProfileStatus.REGISTRATION);
        entity.setRole(ProfileRole.ROLE_USER);
        entity.setCreatedDate(LocalDateTime.now());
        profileRepository.save(entity);
        String jwt = JWTUtil.encode(entity.getEmail(), entity.getRole());
        String text = "Hello. \n To complete registration please link to the following link\n"
                + "http://localhost:8081/auth/verification/email/" + jwt;
        mailSenderService.sendEmail(dto.getEmail(), "Complete registration", text);
        return true;
    }
    public String emailVerification(String jwt) {
        try {
            JwtDTO jwtDTO = JWTUtil.decodeForSpringSecurity(jwt);
            Optional<ProfileEntity> optional = profileRepository.findByEmail(jwtDTO.getEmail());
            if (optional.isEmpty()) {
                throw new AppBadException("account.not.found");
            }
            ProfileEntity entity = optional.get();
            if (!entity.getStatus().equals(ProfileStatus.REGISTRATION)) {
                throw new AppBadException("profile.in.wrong.status");
            }
            profileRepository.updateStatus(entity.getId(), ProfileStatus.ACTIVE);
        } catch (JwtException e) {
            throw new AppBadException("please.tyre.again");
        }
        return null;
    }

    public ProfileDTO login(AuthDTO profile) {
        Optional<ProfileEntity> optional = profileRepository.findByEmailAndPassword(profile.getEmail(),
                MDUtil.encode(profile.getPassword()));

        if (optional.isEmpty()) {
            log.error("email or password wrong {}", profile.getEmail());
            throw new AppBadException("email or password wrong");
        }

        ProfileEntity entity = optional.get();

        ProfileDTO dto = new ProfileDTO();

        if (entity.getStatus().equals(ProfileStatus.BLOCKED)) {
            log.info("account blocked {}",profile.getEmail() );
            throw new AppBadException("account blocked");
        }

        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setRole(entity.getRole());
        dto.setEmail(entity.getEmail());
        dto.setJwt(JWTUtil.encode(entity.getEmail(), entity.getRole()));
        return dto;
    }
}
