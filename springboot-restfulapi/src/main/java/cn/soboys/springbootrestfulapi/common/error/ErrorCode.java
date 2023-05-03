package cn.soboys.springbootrestfulapi.common.error;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/5/2 21:33
 * @webSite https://github.com/coder-amiao
 * 错误码接口，凡各模块错误码枚举类，皆须为此接口的子类型
 */
public interface ErrorCode {
    Integer getCode();

    String getMessage();

    boolean getSuccess();
}
