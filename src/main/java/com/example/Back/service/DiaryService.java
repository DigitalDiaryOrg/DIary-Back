package com.example.Back.service;

import com.example.Back.entity.Diary;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.Optional;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    @Autowired
    DiaryService(DiaryRepository diaryRepository){
        this.diaryRepository=diaryRepository;
    }

    public Optional<Diary> get(String dateStr, String email) throws ParseException {

        return diaryRepository.findDiaryByDateAndMember_Email(DateUtil.convertStrToDate(dateStr), email);
    }

    public void save(Diary diary){
        diaryRepository.save(diary);
    }

    public void delete(String dateStr, String email) throws ParseException {
        Diary diary = this.get(dateStr, email).orElseThrow();
        diaryRepository.delete(diary);
    }




}
