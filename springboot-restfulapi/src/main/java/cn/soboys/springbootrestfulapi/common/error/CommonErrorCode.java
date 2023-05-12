package cn.soboys.springbootrestfulapi.common.error;

import cn.soboys.springbootrestfulapi.common.resp.ResultCode;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/2 21:36
 * @webSite https://github.com/coder-amiao
 * 通用公共错误枚举模块
 */
public enum CommonErrorCode implements ResultCode {

    /**
     * 错误请求
     */
    INVALID_REQUEST(false, CommonErrorConstant.InvalidRequest, "Invalid request, for reason: "),
    /**
     * 参数验证错误
     */
    INVALID_ARGUMENT(false, CommonErrorConstant.InvalidArgument, "Validation failed for argument "),
    /**
     * 未找到资源
     */
    NOT_FOUND(false, CommonErrorConstant.NotFound, "Resource  not found."),
    /**
     * 未知错误
     */
    UNKNOWN_ERROR(false, CommonErrorConstant.UnknownError, "Unknown server internal error.");


    CommonErrorCode(Boolean success, String code, String message) {
        this.success = success;
        this.code = code;
        this.message = message;

    }

    /**
     * 响应是否成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 响应信息
     */
    private String message;


    @Override
    public String getCode() {
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


}
