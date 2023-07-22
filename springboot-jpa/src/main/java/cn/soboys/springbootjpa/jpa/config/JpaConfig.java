package cn.soboys.springbootjpa.jpa.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 15:21
 * @webSite https://github.com/coder-amiao
 * 每当你保存或更新实体时，createdDate 和 lastModifiedDate 字段就会自动更新。
 * @CreatedDate 只在实体首次持久化时设置，而 @LastModifiedDate 在每次更新实体时都会被设置。
 */
@Configuration
@EnableJpaAuditing
public class JpaConfig {
}
