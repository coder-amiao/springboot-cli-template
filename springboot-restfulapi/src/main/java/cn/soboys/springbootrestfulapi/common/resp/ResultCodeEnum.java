package cn.soboys.springbootrestfulapi.common.resp;

import cn.soboys.springbootrestfulapi.common.error.ErrorCode;
import lombok.Data;
import lombok.Getter;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/28 22:39
 * @webSite https://github.com/coder-amiao
 * 响应结果枚举
 */
public enum ResultCodeEnum implements ErrorCode {


    SUCCESS(true, 200, "成功"),

    FAIL(false, 400, "请求失败");


    /**
     * 响应是否成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private Integer code;
    /**
     * 响应信息
     */
    private String message;


    @Override
    public Integer getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public boolean getSuccess() {
        return success;
    }


    ResultCodeEnum(Boolean success, Integer code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;
    }
}
