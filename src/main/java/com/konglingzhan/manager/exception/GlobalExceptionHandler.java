package com.konglingzhan.manager.exception;

import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)// 拦截所有异常
    public Result exceptionHandler(Exception e) {
        log.info("Exception");
        if (e instanceof CommonException) {
            // 自定义异常
            return new Result(((CommonException)e).getCode(),((CommonException) e).getMsg(),null);
        } else if(e instanceof BindException){
            // 参数异常
            String message = ((BindException)e).getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
            return new Result(CodeMsg.CODE_FAIL,message,null);
        } else {
            // 其他异常
            return new Result(CodeMsg.CODE_FAIL,e.getMessage(),null);
        }
    }
}
