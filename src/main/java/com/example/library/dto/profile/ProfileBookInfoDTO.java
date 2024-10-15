package com.example.library.dto.profile;

import com.example.library.enums.ProfileBookStatus;
import lombok.Data;

@Data
public class ProfileBookInfoDTO {
    private String profileName;
    private String profileSurname;
    private String profileEmail;
    private String bookTitle;
    private String bookAuthor;
}
