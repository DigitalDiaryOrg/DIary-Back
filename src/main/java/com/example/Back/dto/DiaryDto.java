package com.example.Back.dto;

import com.example.Back.entity.Diary;
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

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Request{
        private List<String> emotionField;
        private String date;
        private String content;
        private String musicTitle;
        private String musicId;
        private String praise;
        private String email;
    }
}
