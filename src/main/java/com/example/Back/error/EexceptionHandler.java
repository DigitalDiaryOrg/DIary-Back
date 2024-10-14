package com.example.Back.error;

import com.example.Back.dto.DiaryDto;
import com.example.Back.dto.ResponseDto;
import com.example.Back.util.ResponseUtils;
import io.swagger.v3.oas.models.responses.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class EexceptionHandler {
    @ExceptionHandler
    public ResponseDto.Response<ResponseDto.Error> handleAllException(Exception e){
        log.warn(e.getMessage());
        return ResponseUtils.error(HttpStatus.INTERNAL_SERVER_ERROR.value(), e.getMessage());
    }
}
