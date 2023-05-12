package cn.soboys.springbootrestfulapi.common.error;
import lombok.Data;
/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/9 21:26
 * @webSite https://github.com/coder-amiao
 *  未知异常错误响应封装
 */
@Data
public class ErrorDetail {
    /**
     * 响应是否成功
     */
    private Boolean success;
    /**
     * 响应状态码
     */
    private String code;
    /**
     * 错误信息
     */
    private String message;
    /**
     * 错误描述
     */
    private String details;
    /**
     * 错误信息
     */
    private String errorMsg;


    /**
     * 接口请求时间戳
     */
    private String timestamp;
}
