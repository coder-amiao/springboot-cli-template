package cn.soboys.springbootrestfulapi.common.exception;


import cn.hutool.core.collection.CollectionUtil;
import cn.soboys.springbootrestfulapi.common.error.CommonErrorCode;
import cn.soboys.springbootrestfulapi.common.resp.R;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import java.util.List;
import java.util.stream.Collectors;


/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/29 00:21
 * @webSite https://github.com/coder-amiao
 * 统一异常处理器
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 通用异常处理方法
     **/
    @ExceptionHandler(Exception.class)
    @ResponseBody
    public R error(Exception e, WebRequest request) {
        e.printStackTrace();
        return R.setResult(CommonErrorCode.INTERNAL_SERVER_ERROR)
                .request(request.getDescription(true))
                .errorMsg(e.getMessage());
    }



    /**
     * 处理 form data方式调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R BindExceptionHandler(BindException e) {
        String message = e.getBindingResult().getAllErrors().stream().map(DefaultMessageSourceResolvable::getDefaultMessage).collect(Collectors.joining());
        return R.failure().code(CommonErrorCode.PARAM_ERROR.getCode()).message(message);
    }

    /**
     * 处理Get请求中 验证路径中 单个参数请求失败抛出异常
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        String message = e.getConstraintViolations().stream().map(ConstraintViolation::getMessage).collect(Collectors.joining());
        return R.failure().code(CommonErrorCode.PARAM_ERROR.getCode()).message(message);
    }


    /**
     * 处理 json 请求体调用接口对象参数校验失败抛出的异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R jsonParamsException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        String msg=";";

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
             msg = String.format("%s%s；", fieldError.getField(), fieldError.getDefaultMessage())+msg;
        }
        return R.failure().code(CommonErrorCode.PARAM_ERROR.getCode()).message(msg);
    }

    /**
     * 自定义异常处理方法
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R error(BusinessException e) {
        e.printStackTrace();
        return R.failure().message(e.getMessage()).code(e.getCode());
    }

}
