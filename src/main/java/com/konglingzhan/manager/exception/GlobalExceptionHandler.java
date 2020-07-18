package com.konglingzhan.manager.exception;

import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.stream.Collectors;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)// 拦截所有异常
    public Result exceptionHandler(HttpServletRequest request , HttpServletResponse response, Exception e) {

        if (e instanceof CommonException) {
            log.info("CommonException");
            // 自定义异常
            return new Result(((CommonException)e).getCode(),((CommonException) e).getMsg(),null);
        } else if(e instanceof ParamException){
            log.info("ParamException");
            // 参数异常
            return new Result(CodeMsg.CODE_FAIL,e.getMessage(),null);
        } else {
            log.info("OtherException");
            // 其他异常
            return new Result(CodeMsg.CODE_FAIL,e.getMessage(),null);
        }
    }
}
