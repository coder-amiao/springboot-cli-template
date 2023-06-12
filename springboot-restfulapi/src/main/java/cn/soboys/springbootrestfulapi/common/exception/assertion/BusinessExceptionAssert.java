package cn.soboys.springbootrestfulapi.common.exception.assertion;

import cn.hutool.core.util.ArrayUtil;
import cn.soboys.springbootrestfulapi.common.exception.BaseException;
import cn.soboys.springbootrestfulapi.common.exception.BusinessException;
import cn.soboys.springbootrestfulapi.common.resp.ResultCode;

import java.text.MessageFormat;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/6/12 10:52 下午
 * @webSite https://github.com/coder-amiao
 */
public interface BusinessExceptionAssert extends ResultCode, Assert {
    @Override
    default BaseException newException(Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new BusinessException(this, args, msg);
    }

    @Override
    default BaseException newException(Throwable t, Object... args) {
        String msg = this.getMessage();
        if (ArrayUtil.isNotEmpty(args)) {
            msg = MessageFormat.format(this.getMessage(), args);
        }

        return new BusinessException(this, args, msg, t);
    }
}

