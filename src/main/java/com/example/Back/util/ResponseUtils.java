package com.example.Back.util;

import com.example.Back.dto.ResponseDto;

public class ResponseUtils {
    public static <T> ResponseDto.Response<T> ok(T data){
        ResponseDto.Response<T> response = new ResponseDto.Response<>();
        response.setSuccess(true);
        response.setData(data);
        return  response;
    }

    public static <T> ResponseDto.Response<T> error(int code, String message){
        ResponseDto.Response<T> response = new ResponseDto.Response<>();
        response.setSuccess(false);
        response.setError(new ResponseDto.Error(message, code));
        return response;
    }
}
