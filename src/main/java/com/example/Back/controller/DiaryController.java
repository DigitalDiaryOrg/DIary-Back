package com.example.Back.controller;

import com.example.Back.dto.DiaryDto;
import com.example.Back.dto.ResponseDto;
import com.example.Back.service.DiaryService;
import com.example.Back.util.ResponseUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/diary")
public class DiaryController {
    @Autowired
    DiaryService diaryService;

    public DiaryController(DiaryService diaryService){
        this.diaryService = diaryService;
    }
//    일기 조회
//    유저 이메일 + 날짜
    @GetMapping("/{date}")
    public ResponseDto.Response<DiaryDto.Response> getDiary(@PathVariable String date){
        return ResponseUtils.ok(new DiaryDto.Response(diaryService.get(date, "email")));
    }

//    일기 등록
//    @PostMapping("")

//    일기 수정
//    @PostMapping
}
