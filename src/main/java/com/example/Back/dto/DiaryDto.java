package com.example.Back.dto;

import com.example.Back.entity.Diary;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

public class DiaryDto {

    @Getter
    @Setter
    public static class Response{
        private List<String> emotionField;
        private String musicTitle;
        private String musicId;
        private String content;
        private String praise;

        public Response(Diary diary){
            this.emotionField = diary.getEmotionField();
            this.musicId = diary.getMusicId();
            this.musicTitle = diary.getMusicTitle();
            this.content = diary.getContent();
            this.praise = diary.getPraise();
        }
    }

    @Schema( title = "일기 작성 DTO", description = "일기 작성 DTO")
    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        @Schema(description = "감정 리스트", example = "[\"우울\", \"그저그럼\"]")
        private List<String> emotionField;
        @Schema(description = "작성 날짜", example = "2024-10-15")
        private String date;
        @Schema(description = "내용", example = "우산 안가져왔는데 비가 왔다")
        private String content;
        @Schema(description = "음악 이름", example = "비온다")
        private String musicTitle;
        @Schema(description = "음악 아이디", example = "TFIlgKMI9NE")
        private String musicId;
        @Schema(description = "오늘의 칭찬", example = "씩씩하게 비를 맞았다")
        private String praise;
        @Schema(description = "이메일", example = "kimkim@gmail.com")
        private String email;
    }
}
