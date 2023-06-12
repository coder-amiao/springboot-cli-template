package cn.soboys.springbootrestfulapi.common.exception;


import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.date.DateUtil;
import cn.hutool.core.exceptions.ExceptionUtil;
import cn.hutool.core.util.StrUtil;
import cn.soboys.springbootrestfulapi.common.error.CommonErrorCode;
import cn.soboys.springbootrestfulapi.common.error.ErrorDetail;
import cn.soboys.springbootrestfulapi.common.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;


import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Path;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;


/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/29 00:21
 * @webSite https://github.com/coder-amiao
 * 统一异常处理器
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    /**
     * 未知错误异常处理方法
     * <p>
     * 这里需根据业务打印错误日志输出到对应平台
     **/
    @ExceptionHandler(Exception.class)
    public ErrorDetail exception(Exception e, WebRequest request) {
        log.error("系统未知错误{}", ExceptionUtil.stacktraceToString(e));
        ErrorDetail errorDetail = new ErrorDetail();
        errorDetail.setDetails(request.getDescription(true));
        errorDetail.setErrorMsg(e.getMessage());
        errorDetail.setMessage(CommonErrorCode.UNKNOWN_ERROR.getMessage());
        errorDetail.setCode(CommonErrorCode.UNKNOWN_ERROR.getCode());
        errorDetail.setSuccess(CommonErrorCode.UNKNOWN_ERROR.getSuccess());
        errorDetail.setTimestamp(DateUtil.now());
        return errorDetail;

    }


    /**
     * post 请求
     * 处理 x-www-form-urlencoded 类型参数 解析验证异常
     * 提交的数据按照 key1=val1&key2=val2 的方式进行编码，key 和 val 都进行了 URL 转码
     */
    @ExceptionHandler(BindException.class)
    @ResponseBody
    public R BindExceptionHandler(BindException e) {
        List<FieldError> fieldErrors = e.getBindingResult().getFieldErrors();
        List<String> collect = fieldErrors.stream()
                .map(o -> o.getField() + o.getDefaultMessage())
                .collect(Collectors.toList());

        return R.failure().code(CommonErrorCode.INVALID_ARGUMENT.getCode())
                .message(CommonErrorCode.INVALID_ARGUMENT.getMessage() + " "
                        + CollUtil.join(collect, ";"));
    }

    /**
     * Get请求中  单个参数 验证 失败异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public R ConstraintViolationExceptionHandler(ConstraintViolationException e) {
        List errorList = CollectionUtil.newArrayList();
        Set<ConstraintViolation<?>> violations = e.getConstraintViolations();
        for (ConstraintViolation<?> violation : violations) {
            StringBuilder message = new StringBuilder();
            Path path = violation.getPropertyPath();
            String[] pathArr = StrUtil.splitToArray(path.toString(), ".");
            String msg = "";
            if (pathArr.length >= 3) {
                msg = message.append(pathArr[pathArr.length - 1]).append(violation.getMessage()).toString();
            } else {
                msg = message.append(pathArr[1]).append(violation.getMessage()).toString();
            }
            errorList.add(msg);
        }

        return R.failure().code(CommonErrorCode.INVALID_ARGUMENT.getCode())
                .message(CommonErrorCode.INVALID_ARGUMENT.getMessage() + " "
                        + CollUtil.join(errorList, ";"));
    }


    /**
     * post 请求 body JSON 参数
     * 实体类JSON参数映射,单个参数验证失败异常
     */
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public R jsonParamsException(MethodArgumentNotValidException e) {
        BindingResult bindingResult = e.getBindingResult();
        List errorList = CollectionUtil.newArrayList();

        for (FieldError fieldError : bindingResult.getFieldErrors()) {
            String msg = String.format("%s%s；", fieldError.getField(), fieldError.getDefaultMessage());
            errorList.add(msg);
        }

        return R.failure().code(CommonErrorCode.INVALID_ARGUMENT.getCode())
                .message(CommonErrorCode.INVALID_ARGUMENT.getMessage() + " "
                        + CollUtil.join(errorList, ";"));
    }


    /**
     * 接口不存在 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseBody
    public R error(NoHandlerFoundException e) {
        e.printStackTrace();
        return R.failure().code(CommonErrorCode.NOT_FOUND.getCode()).message(CommonErrorCode.NOT_FOUND.getMessage());
    }

    /**
     * 请求方法不被允许异常
     */
    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public R httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e) {
        return R.failure().code(CommonErrorCode.INVALID_REQUEST.getCode()).message(CommonErrorCode.INVALID_REQUEST.getMessage() + e.getMessage());
    }

    /**
     * 请求与响应媒体类型不一致 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
    public R httpMediaTypeNotSupportedException(HttpMediaTypeNotSupportedException e) {
        return R.failure().code(CommonErrorCode.INVALID_REQUEST.getCode()).message(CommonErrorCode.INVALID_REQUEST.getMessage() + e.getMessage());
    }

    /**
     * post请求 缺少body json参数 异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public R HttpMessageNotReadableException(HttpMessageNotReadableException e) {
        return R.failure().code(CommonErrorCode.INVALID_REQUEST.getCode()).message(CommonErrorCode.INVALID_REQUEST.getMessage() + e.getMessage() + " "
                + "缺少body 请求JSON参数");
    }


    /**
     * 自定义异常处理方法
     */


    /**
     * 统一业务异常处理
     *
     * @param e
     * @return
     */
    @ExceptionHandler(BusinessException.class)
    @ResponseBody
    public R error(BaseException e) {
        return R.failure().code(e.getResultCode().getCode()).message(e.getMessage());
    }


}
