package com.example.library.controller;

import com.example.library.dto.AuthDTO;
import com.example.library.dto.profile.ProfileDTO;
import com.example.library.dto.RegistrationDTO;
import com.example.library.service.AuthService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {
    private final  AuthService authService;

    @PostMapping("/login")
    public ResponseEntity<ProfileDTO> login(@RequestBody AuthDTO authDTO) {
        log.info("login {}", authDTO.getEmail());
        return ResponseEntity.ok(authService.login(authDTO));
    }

    @PostMapping("/registration")
    public ResponseEntity<Boolean> registration(@RequestBody RegistrationDTO dto) {
        log.info("registration: {}", dto.getEmail());
        return ResponseEntity.ok(authService.registration(dto));
    }
    @GetMapping("/verification/email/{jwt}")
    public ResponseEntity<String> emailVerification(@PathVariable("jwt") String jwt){
        return ResponseEntity.ok(authService.emailVerification(jwt));
    }
}
