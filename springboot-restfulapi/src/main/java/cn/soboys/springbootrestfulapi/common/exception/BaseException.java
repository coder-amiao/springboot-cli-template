package cn.soboys.springbootrestfulapi.common.exception;

import cn.soboys.springbootrestfulapi.common.resp.ResultCode;
import lombok.Data;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/6/12 10:32 下午
 * @webSite https://github.com/coder-amiao
 */
@Data
public class BaseException extends RuntimeException {
    /**
     * 返回码
     */
    protected ResultCode resultCode;
    /**
     * 异常消息参数
     */
    protected Object[] args;

    public BaseException(ResultCode resultCode) {
        super(resultCode.getMessage());
        this.resultCode = resultCode;
    }


    public BaseException(String code, String msg) {
        super(msg);
        this.resultCode = new ResultCode() {
            @Override
            public String getCode() {
                return code;
            }

            @Override
            public String getMessage() {
                return msg;
            }

            @Override
            public boolean getSuccess() {
                return false;
            }

            ;
        };
    }

    public BaseException(ResultCode resultCode, Object[] args, String message) {
        super(message);
        this.resultCode = resultCode;
        this.args = args;
    }

    public BaseException(ResultCode resultCode, Object[] args, String message, Throwable cause) {
        super(message, cause);
        this.resultCode = resultCode;
        this.args = args;
    }

}
