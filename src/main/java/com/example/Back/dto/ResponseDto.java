package com.example.Back.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.web.ErrorResponse;

public class ResponseDto {
    @Getter
    @Setter
    public static class Response<T>{
        private boolean success;
        private T data;
        private Error error;

        public Response() {

        }
    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Success{
        private String message;

    }

    @Getter
    @Setter
    @AllArgsConstructor
    public static class Error{
        private String message;
        private int code;
    }
}
