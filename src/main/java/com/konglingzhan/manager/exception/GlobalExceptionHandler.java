package com.konglingzhan.manager.exception;

import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = {RuntimeException.class})
    public Result defaultExecptionHandler(RuntimeException e) {
        log.info(e.toString());
        if(e instanceof CommonException){
            return new Result(((CommonException)e).getCode(),((CommonException) e).getMsg(),null);
        }else {
            return new Result(CodeMsg.CODE_FAIL,e.getMessage(),null);
        }
    }
}
