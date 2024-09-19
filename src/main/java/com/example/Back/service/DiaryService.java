package com.example.Back.service;

import com.example.Back.entity.Diary;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DiaryService {
    private final DiaryRepository diaryRepository;
    @Autowired
    DiaryService(DiaryRepository diaryRepository){
        this.diaryRepository=diaryRepository;
    }

    public void save(Diary diary){
        diaryRepository.save(diary);
    }

    public Diary get(String dateStr, String email){
        try {
            return diaryRepository.findDiaryByDateAndMember_Email(DateUtil.convertStrToDate(dateStr), email).orElseThrow();
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }



}
