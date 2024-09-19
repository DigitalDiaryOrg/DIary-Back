package com.example.Back.dto;

import com.example.Back.entity.Diary;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

public class DiaryDto {

    @Getter
    @Setter
    public static class Response{
        public List<String> emotionField;
        public String musicTitle;
        public String musicId;
        public String content;
        public String praise;

        public Response(Diary diary){
            this.emotionField = diary.getEmotionField();
            this.musicId = diary.getMusicId();
            this.musicTitle = diary.getMusicTitle();
            this.content = diary.getContent();
            this.praise = diary.getPraise();
        }
    }
}
