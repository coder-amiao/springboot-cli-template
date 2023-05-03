package cn.soboys.springbootrestfulapi.common.error;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/2 21:36
 * @webSite https://github.com/coder-amiao
 */
public enum CommonErrorCode implements ErrorCode {

    NOT_FOUND(false, 404, "接口不存在"),
    FORBIDDEN(false, 403, "资源拒绝访问"),
    UNAUTHORIZED(false, 401, "未认证（签名错误）"),
    INTERNAL_SERVER_ERROR(false, 500, "服务网络不可用"),
    PARAM_ERROR(false, 110001, "参数错误");



    CommonErrorCode(Boolean success, Integer code, String message) {
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


}
