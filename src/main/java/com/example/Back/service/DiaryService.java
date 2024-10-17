package com.example.Back.service;

import com.example.Back.dto.DiaryDto;
import com.example.Back.entity.Diary;
import com.example.Back.entity.Member;
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

    public void save(Member member, DiaryDto.Request diaryReqDto) throws ParseException {

        Diary newDiary = Diary.builder()
                .member(member)
                .emotionField(diaryReqDto.getEmotionField())
                .musicId(diaryReqDto.getMusicId())
                .musicTitle(diaryReqDto.getMusicTitle())
                .content(diaryReqDto.getContent())
                .praise(diaryReqDto.getPraise())
                .date(DateUtil.convertStrToDate(diaryReqDto.getDate()))
                .build();

        Optional<Diary> oldDiary = this.get(diaryReqDto.getDate(), diaryReqDto.getEmail());

        if(oldDiary.isPresent()){
            diaryRepository.save(oldDiary.get().update(newDiary));
        }else{
            diaryRepository.save(newDiary);
        }
    }

    public void delete(String dateStr, String email) throws ParseException {
        Diary diary = this.get(dateStr, email).orElseThrow();
        diaryRepository.delete(diary);
    }




}
