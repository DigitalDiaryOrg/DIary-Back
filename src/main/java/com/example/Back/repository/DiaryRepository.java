package com.example.Back.repository;

import com.example.Back.entity.Diary;
import org.springframework.data.repository.ListCrudRepository;

import java.util.Date;
import java.util.Optional;

public interface DiaryRepository extends ListCrudRepository<Diary, Long> {
    public Optional<Diary> findDiaryByDateAndMember_Email(Date date, String email);


}
