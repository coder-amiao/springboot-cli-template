package cn.soboys.springbootrestfulapi.common.exception;


import cn.soboys.springbootrestfulapi.common.resp.ResultCode;
import lombok.Data;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/29 00:15
 * @webSite https://github.com/coder-amiao
 * 通用业务异常封装
 */
@Data
public class BusinessException extends RuntimeException {
    /**
     * 自定义异常编码
     */
    private String code;

    public BusinessException(String code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ResultCode resultCodeEnum) {
        super(resultCodeEnum.getMessage());
        this.code = resultCodeEnum.getCode();
    }

    public BusinessException(ResultCode resultCodeEnum, String msg) {
        super(resultCodeEnum.getMessage() +" " +msg);
        this.code = resultCodeEnum.getCode();
    }
}
