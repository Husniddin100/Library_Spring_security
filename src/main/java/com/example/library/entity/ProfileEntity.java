package com.example.library.entity;

import com.example.library.enums.ProfileRole;
import com.example.library.enums.ProfileStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile")
public class ProfileEntity {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "surname")
    private String surname;

    @Column(name = "email",unique = true)
    private String email;

    @Column(name = "password")
    private String password;
    @Enumerated(EnumType.STRING)
    @Column(name = "profile_status")
    private ProfileStatus status;

    @Enumerated(EnumType.STRING)
    @Column(name = "profile_role")
    private ProfileRole role;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

}
