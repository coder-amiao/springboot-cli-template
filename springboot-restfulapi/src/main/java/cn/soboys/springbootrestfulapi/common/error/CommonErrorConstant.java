package cn.soboys.springbootrestfulapi.common.error;

import java.util.List;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/9 20:14
 * @webSite https://github.com/coder-amiao
 * 定义错误常量 代替错误码,避免业务复杂错误码分配重复等问题
 */
public interface CommonErrorConstant {
    /**
     * 公共错误码定义
     */
    public static final String InvalidRequest = "InvalidRequest";
    public static final String InvalidArgument = "InvalidArgument";
    public static final String NotFound = "NotFound";
    public static final String UnknownError = "UnknownError";
    public static final String OK = "OK";
    public static final String FAIL = "FAIL";



    /**
     * 其他自定义业务错误码
     */



}
