package cn.soboys.springbootrestfulapi.common.exception;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/6/12 10:49 下午
 * @webSite https://github.com/coder-amiao
 * 只包装了 错误信息 的 {@link RuntimeException}.
 *  用于包装自定义异常信息
 */
public class WrapMessageException extends RuntimeException{

    public WrapMessageException(String message) {
        super(message);
    }

    public WrapMessageException(String message, Throwable cause) {
        super(message, cause);
    }
}
