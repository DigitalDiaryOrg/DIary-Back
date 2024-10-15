package com.example.Back.controller;

import com.example.Back.dto.DiaryDto;
import com.example.Back.dto.ResponseDto;
import com.example.Back.entity.Diary;
import com.example.Back.entity.Member;
import com.example.Back.service.DiaryService;
import com.example.Back.service.MemberService;
import com.example.Back.util.ResponseUtils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.Optional;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    private final DiaryService diaryService;
    private final MemberService memberService;

    @Autowired
    public DiaryController(DiaryService diaryService, MemberService memberService){
        this.diaryService = diaryService;
        this.memberService = memberService;
    }

    @Operation(summary = "일기 조회 API")
    @GetMapping("/{date}/{email}")
    public ResponseDto.Response<DiaryDto.Response> getDiary(
            @Parameter(description = "작성 날짜", example = "2024-10-15") @PathVariable("date") String date,
            @Parameter(description = "사용자 이메일", example = "email1@mail.com") @PathVariable("email") String email) throws ParseException {
        Diary diary= diaryService.get(date, email).orElseThrow();
        return ResponseUtils.ok(new DiaryDto.Response(diary));
    }

    @Operation(summary = "일기 작성 API")
    @PostMapping("/post")
    public ResponseDto.Response<ResponseDto.Success> postDiary(@RequestBody DiaryDto.Request diaryReqDto) throws ParseException {
        Optional<Diary> diary= diaryService.get(diaryReqDto.getDate(), diaryReqDto.getEmail());

        if(diary.isEmpty()) {
            Member member = memberService.get(diaryReqDto.getEmail());
            Diary newDiary = Diary.builder()
                    .member(member)
                    .emotionField(diaryReqDto.getEmotionField())
                    .musicId(diaryReqDto.getMusicId())
                    .musicTitle(diaryReqDto.getMusicTitle())
                    .content(diaryReqDto.getContent())
                    .praise(diaryReqDto.getPraise())
                    .build();
            diaryService.save(newDiary);
        }else{
            Diary oldDiary = diary.get();
            oldDiary.update(diaryReqDto);
            diaryService.save(oldDiary);
        }
        return ResponseUtils.ok(new ResponseDto.Success("일기 작성 완료"));
    }

    @Operation(summary = "일기 삭제 API")
    @PostMapping("/delete")
    public ResponseDto.Response<ResponseDto.Success> deleteDiary(
            @Parameter(description = "작성 날짜", example = "2024-10-15") @RequestParam("date") String date,
            @Parameter(description = "사용자 이메일", example = "email2@mail.com") @RequestParam("email") String email) throws ParseException {
        diaryService.delete(date, email);
        return ResponseUtils.ok(new ResponseDto.Success("삭제 완료"));
    }
}
