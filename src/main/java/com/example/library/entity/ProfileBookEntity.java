package com.example.library.entity;

import com.example.library.enums.ProfileBookStatus;
import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.GenericGenerator;

import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "profile_book")
public class ProfileBookEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "profile_id")
    private String profileId;

    @ManyToOne
    @JoinColumn(name = "profile_id",insertable = false,updatable = false)
    private ProfileEntity profile;

    @Column(name = "book_id")
    private String bookId;

    @ManyToOne
    @JoinColumn(name = "book_id",insertable = false,updatable = false)
    private BookEntity book;

    @Column(name = "created_date")
    private LocalDateTime createdDate;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private ProfileBookStatus status;

    @Column(name = "returned_date")
    private LocalDateTime returnedDate;

}
