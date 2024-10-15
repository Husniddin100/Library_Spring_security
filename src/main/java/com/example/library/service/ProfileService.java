package com.example.library.service;

import com.example.library.dto.profile.ProfileDTO;
import com.example.library.entity.ProfileEntity;
import com.example.library.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class ProfileService {
    private final ProfileRepository profileRepository;

    public List<ProfileDTO> getAll() {
        var profiles = profileRepository.findAll();

        List<ProfileDTO> profileDTOS = new ArrayList<>();
        for (ProfileEntity profile : profiles) {
            profileDTOS.add(toDTO(profile));
        }
        return profileDTOS;
    }

    public ProfileDTO toDTO(ProfileEntity entity) {
        ProfileDTO dto = new ProfileDTO();
        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setSurname(entity.getSurname());
        dto.setEmail(entity.getEmail());
        dto.setCreatedDate(entity.getCreatedDate());
        return dto;
    }
}
