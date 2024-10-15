package com.example.library.repository;

import com.example.library.entity.ProfileBookEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProfileBookRepository extends CrudRepository<ProfileBookEntity,Integer> {

    @Query("from ProfileBookEntity where profileId=?1  ")
    List<ProfileBookEntity> findByProfileId(String id);
}
