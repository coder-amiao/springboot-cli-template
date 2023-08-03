package cn.soboys.springbootjpa;

import cn.soboys.simplestjpa.plugin.TenantFactory;
import org.springframework.context.annotation.Configuration;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/30 22:15
 * @webSite https://github.com/coder-amiao
 */
@Configuration
public class TenantConfig implements TenantFactory {

    @Override
    public String getTenantId() {
        return "8";
    }
}
