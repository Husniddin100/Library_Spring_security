package com.example.library.service;

import com.example.library.dto.profile.ProfileBookDTO;
import com.example.library.dto.profile.ProfileBookInfoDTO;
import com.example.library.entity.BookEntity;
import com.example.library.entity.ProfileBookEntity;
import com.example.library.entity.ProfileEntity;
import com.example.library.enums.ProfileBookStatus;
import com.example.library.repository.BookRepository;
import com.example.library.repository.ProfileBookRepository;
import com.example.library.repository.ProfileRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
@RequiredArgsConstructor
public class ProfileBookService {

    private final ProfileBookRepository profileBookRepository;

    public ProfileBookDTO getBook(ProfileBookDTO dto) {
        ProfileBookEntity entity = new ProfileBookEntity();
        entity.setBookId(dto.getBookId());
        entity.setProfileId(dto.getProfileId());
        entity.setStatus(dto.getStatus());
        entity.setCreatedDate(LocalDateTime.now());
        entity.setStatus(ProfileBookStatus.TAKEN);

        log.info("attach book to profile {} {}", dto.getBookId(), dto.getProfileId());

        profileBookRepository.save(entity);
        dto.setId(entity.getId());
        return dto;
    }


    public ProfileBookEntity returnBook(Integer id) {
        ProfileBookEntity book = profileBookRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Book not found"));

        book.setStatus(ProfileBookStatus.RETURNED);
        book.setReturnedDate(LocalDateTime.now());

        log.info("Book returned: {} {}", book.getBookId(), book.getProfileId());
        return profileBookRepository.save(book);

    }

    public List<ProfileBookInfoDTO> getBooksByStudentId(String profileId) {
        List<ProfileBookEntity> profileBookEntities = profileBookRepository.findByProfileId(profileId);

        return profileBookEntities.stream().map(profileBook -> {
            ProfileEntity profile = profileBook.getProfile();
            BookEntity book = profileBook.getBook();
            ProfileBookInfoDTO dto = new ProfileBookInfoDTO();
            dto.setProfileName(profile.getName());
            dto.setProfileSurname(profile.getSurname());
            dto.setProfileEmail(profile.getEmail());
            dto.setBookTitle(book.getTitle());
            dto.setBookAuthor(book.getAuthor());

            return dto;
        }).collect(Collectors.toList());
    }
}
