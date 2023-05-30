package cn.soboys.springbootrestfulapi.common.error;


import cn.soboys.springbootrestfulapi.common.resp.ResultCode;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/14 23:01
 * @webSite https://github.com/coder-amiao
 */
public enum BusinessErrorCode implements ResultCode {

    Sign_Error(false, CommonErrorConstant.SignError, "接口签名认证无效 ");


    BusinessErrorCode(Boolean success, String code, String message) {
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
