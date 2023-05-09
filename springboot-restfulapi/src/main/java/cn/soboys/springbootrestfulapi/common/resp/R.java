package cn.soboys.springbootrestfulapi.common.resp;

import cn.hutool.core.date.DateUtil;
import cn.soboys.springbootrestfulapi.common.error.ErrorDetail;
import lombok.Data;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/28 22:47
 * @webSite https://github.com/coder-amiao
 * 统一响应结果处理  使用链式编程 返回类本身
 */
@Getter
public class R {

    private Boolean success;

    private String code;

    private String message;


    /**
     * 接口请求时间戳
     */
    private String timestamp;


    private Map<String, Object> data = new HashMap<>();


    private R setSuccess(Boolean success) {
        this.success = success;
        return this;
    }


    private R setMessage(String message) {
        this.message = message;
        return this;
    }


    private R setData(Map<String, Object> data) {
        this.data = data;
        return this;
    }

    private R setCode(String code) {
        this.code = code;
        return this;
    }

    private R() {
    }

    private R(String timestamp) {
        this.timestamp = timestamp;
    }


    // 自定义返回数据
    public R data(Map<String, Object> map) {
        return this.setData(map);

    }

    // 通用设置data
    public R data(String key, Object value) {
        this.data.put(key, value);
        return this;
    }

    // 自定义状态信息
    public R message(String message) {
        return this.setMessage(message);

    }

    // 自定义状态码
    public R code(String code) {
        return this.setCode(code);

    }

    // 自定义返回结果
    public R success(Boolean success) {
        return this.setSuccess(success);

    }


    /**
     * 通用返回成功
     *
     * @return
     */
    public static R success() {
        return new R(DateUtil.now())
                .setSuccess(ResultSuccess.SUCCESS.getSuccess())
                .setCode(ResultSuccess.SUCCESS.getCode())
                .setMessage(ResultSuccess.SUCCESS.getMessage());

    }


    /**
     * 通用返回失败
     *
     * @return
     */
    public static R failure() {
        return new R(DateUtil.now())
                .setSuccess(ResultSuccess.FAIL.getSuccess())
                .setCode(ResultSuccess.FAIL.getCode())
                .setMessage(ResultSuccess.FAIL.getMessage())
                .setData(null);

    }


    /**
     * 设置结果，形参为结果枚举
     *
     * @param result
     * @return
     */
    public static R setResult(ResultCode result) {
        return new R(DateUtil.now())
                .setSuccess(result.getSuccess())
                .setCode(result.getCode())
                .setMessage(result.getMessage());


    }
}
