package com.konglingzhan.manager.common.handler;

import com.baomidou.mybatisplus.core.toolkit.StringPool;
import com.konglingzhan.manager.common.exception.CommonException;
import com.konglingzhan.manager.common.exception.ParamException;
import com.konglingzhan.manager.vo.CodeMsg;
import com.konglingzhan.manager.vo.Result;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;

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

    /**
     * 统一处理请求参数校验(实体对象传参,form data方式,request body方式)
     *
     * @param e BindException,MethodArgumentNotValidException,HttpMessageNotReadableException,ConstraintViolationException
     * @return Result
     */
    @ExceptionHandler({BindException.class, MethodArgumentNotValidException.class, HttpMessageNotReadableException.class, ConstraintViolationException.class})
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public Result validExceptionHandler(Exception e) {
        StringBuilder message = new StringBuilder();
        if(e instanceof BindException|| e instanceof MethodArgumentNotValidException){
            List<FieldError>  fieldErrors = e instanceof BindException?((BindException)e).getBindingResult().getFieldErrors():((MethodArgumentNotValidException)e).getBindingResult().getFieldErrors();
            for (FieldError error : fieldErrors) {
                message.append(error.getField()).append(error.getDefaultMessage()).append(StringPool.COMMA);
            }
            message = new StringBuilder(message.substring(0, message.length() - 1));
        }else if(e instanceof HttpMessageNotReadableException){
            message = new StringBuilder(e.getMessage().substring(0, e.getMessage().indexOf(StringPool.COLON)));
        }else if(e instanceof ConstraintViolationException){
            Set<ConstraintViolation<?>> violations = ((ConstraintViolationException)e).getConstraintViolations();
            for (ConstraintViolation<?> violation : violations) {
                Path path = violation.getPropertyPath();
                String[] pathArr = StringUtils.splitByWholeSeparatorPreserveAllTokens(path.toString(), StringPool.DOT);
                message.append(pathArr[1]).append(violation.getMessage()).append(StringPool.COMMA);
            }
            message = new StringBuilder(message.substring(0, message.length() - 1));
        }else{
            message.append(e.getMessage());
        }
        return Result.error(message.toString());
    }
}
