package com.example.library.repository;

import com.example.library.entity.ProfileEntity;
import com.example.library.enums.ProfileRole;
import com.example.library.enums.ProfileStatus;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface ProfileRepository extends CrudRepository<ProfileEntity,String> {

    @Modifying
    @Transactional
    @Query("update ProfileEntity set status=?2 where id=?1")
    void updateStatus(String id, ProfileStatus profileStatus);

    Optional<ProfileEntity> findByEmail(String email);

    Optional<ProfileEntity> findByEmailAndPassword(String email, String encode);

    Optional<ProfileEntity> findByRole(ProfileRole role); }
