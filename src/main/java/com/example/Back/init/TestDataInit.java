package com.example.Back.init;

import com.example.Back.entity.Diary;
import com.example.Back.entity.Member;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.util.ArrayList;
import java.util.Arrays;

@Slf4j
public class TestDataInit {
    private final MemberRepository memberRepository;
    private final DiaryRepository diaryRepository;

    public TestDataInit(MemberRepository memberRepository, DiaryRepository diaryRepository) {
        this.memberRepository = memberRepository;
        this.diaryRepository = diaryRepository;
    }

    @EventListener(ApplicationReadyEvent.class)
    public void initData(){
        log.info("test data init");
        Member member1 = Member.builder().email("email1").password("password1").nickname("nickname1").build();
        memberRepository.save(member1);
        Member member2 = Member.builder().email("email2").password("password2").nickname("nickname2").build();
        memberRepository.save(member2);
        Member member3 = Member.builder().email("email3").password("password3").nickname("nickname3").build();
        memberRepository.save(member3);
        Member member4 = Member.builder().email("email4").password("password4").nickname("nickname4").build();
        memberRepository.save(member4);

        diaryRepository.save(Diary.builder().content("content1")
                .emotionField(new ArrayList<>(Arrays.asList("행복", "기쁨")))
                .member(member1).musicId("musicId1").musicTitle("musicTitle1")
                .praise("praise1").build());

        diaryRepository.save(Diary.builder().content("content2")
                .emotionField(new ArrayList<>(Arrays.asList("행복", "기쁨")))
                .member(member2).musicId("musicId2").musicTitle("musicTitle2")
                .praise("praise2").build());

        diaryRepository.save(Diary.builder().content("content3")
                .emotionField(new ArrayList<>(Arrays.asList("행복", "기쁨")))
                .member(member3).musicId("musicId3").musicTitle("musicTitle3")
                .praise("praise3").build());
        
        diaryRepository.save(Diary.builder().content("content4")
                .emotionField(new ArrayList<>(Arrays.asList("행복", "기쁨")))
                .member(member4).musicId("musicId4").musicTitle("musicTitle4")
                .praise("praise4").build());
    }

}
