package com.example.library.controller;

import com.example.library.dto.profile.ProfileBookDTO;
import com.example.library.dto.profile.ProfileBookInfoDTO;
import com.example.library.entity.ProfileBookEntity;
import com.example.library.service.ProfileBookService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Slf4j
@RequiredArgsConstructor
@RestController
@RequestMapping("/profile-book")
public class ProfileBookController {
    private final ProfileBookService profileBookService;

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/get-a-book")
    public ResponseEntity<ProfileBookDTO> getBook(@RequestBody ProfileBookDTO dto) {
        log.info("get-a-book {} {}", dto.getProfileId(), dto.getBookId());
        return ResponseEntity.ok(profileBookService.getBook(dto));
    }

    @PreAuthorize("hasRole('USER')")
    @PostMapping("/return-book/{id}")
    public ResponseEntity<ProfileBookEntity> returnBook(@PathVariable Integer id) {
        log.info("return-book {}", id);
        return ResponseEntity.ok(profileBookService.returnBook(id));
    }
    @GetMapping("/profile/{id}")
    public List<ProfileBookInfoDTO> getBooksByProfileId(@PathVariable String id) {
        return profileBookService.getBooksByStudentId(id);
    }


}
