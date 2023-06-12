package cn.soboys.springbootrestfulapi.common.exception;


import cn.soboys.springbootrestfulapi.common.resp.ResultCode;


/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/29 00:15
 * @webSite https://github.com/coder-amiao
 * 通用业务异常封装
 */
public class BusinessException extends BaseException {


    public BusinessException(ResultCode resultCode, Object[] args, String message) {
        super(resultCode, args, message);
    }

    public BusinessException(ResultCode resultCode, Object[] args, String message, Throwable cause) {
        super(resultCode, args, message, cause);
    }

}

