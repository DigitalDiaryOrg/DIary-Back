package com.example.Back.init;

import com.example.Back.entity.Diary;
import com.example.Back.entity.Member;
import com.example.Back.repository.DiaryRepository;
import com.example.Back.repository.MemberRepository;
import com.example.Back.util.DateUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
    public void initData() throws ParseException {
        log.info("test data init");
        Member member1 = Member.builder().email("kimkim@gmail.com").password("password1").nickname("김김이").build();
        memberRepository.save(member1);
        Member member2 = Member.builder().email("samsams@gmail.com").password("password2").nickname("삼삼이").build();
        memberRepository.save(member2);
        Member member3 = Member.builder().email("amu@gmail.com").password("password3").nickname("아무개").build();
        memberRepository.save(member3);
        Member member4 = Member.builder().email("sixsix@gmail.com").password("password4").nickname("육육이").build();
        memberRepository.save(member4);

        Member targetMem = member1;

        for(int i = 0; i<7; i++){
            LocalDate date = LocalDate.now().minusDays(i);
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            String formattedDate = date.format(formatter);
            diaryRepository.save(Diary
                    .builder()
                    .content(targetMem.getNickname()+"의 "+formattedDate+"일의 일기")
                    .emotionField(new ArrayList<>(Arrays.asList("행복", "기쁨")))
                    .member(targetMem)
                    .musicId("musicId"+i)
                    .musicTitle("노래 제목+i")
                    .praise(formattedDate+"일의 칭찬")
                    .date(DateUtil.convertStrToDate(formattedDate))
                    .build());
        }
    }

}
