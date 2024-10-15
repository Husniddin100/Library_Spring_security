package com.example.library.dto.profile;

import com.example.library.enums.ProfileBookStatus;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import java.time.LocalDateTime;
@JsonInclude(JsonInclude.Include.NON_NULL)
@Data
public class ProfileBookDTO {
    private Integer id;
    private String profileId;
    private String bookId;
    private ProfileBookStatus status;
    private LocalDateTime createdDate;
    private LocalDateTime returnedDate;
    private String duration;
}
