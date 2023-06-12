package cn.soboys.springbootrestfulapi.common.handler;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.map.MapUtil;
import cn.soboys.springbootrestfulapi.common.error.ErrorDetail;
import cn.soboys.springbootrestfulapi.common.resp.R;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/6/12 12:17 下午
 * @webSite https://github.com/coder-amiao
 * @Slf4j
 * @ControllerAdvice
 */
@Slf4j
@ControllerAdvice
public class ResponseResultHandler implements ResponseBodyAdvice<Object> {
    /**
     * supports方法: 判断是否要执行beforeBodyWrite方法,
     * true为执行,false不执行.
     * 通过该方法可以选择哪些类或那些方法的response要进行处理, 其他的不进行处理.
     *
     * @param returnType
     * @param converterType
     * @return
     */
    @Override
    public boolean supports(MethodParameter returnType, Class<? extends HttpMessageConverter<?>> converterType) {
        return true;
    }

    /**
     * beforeBodyWrite方法: 对response方法进行具体操作处理
     * 实际返回结果业务包装处理
     *
     * @param body
     * @param returnType
     * @param selectedContentType
     * @param selectedConverterType
     * @param request
     * @param response
     * @return
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class<? extends HttpMessageConverter<?>> selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body instanceof R) {
            return body;
        } else if (body == null) {
            return R.success();
        } else if (body instanceof ErrorDetail) {
            return body;
        } else if (body instanceof String) {
            return body;
        } else {
            return R.success().data(body);
        }
    }
}
