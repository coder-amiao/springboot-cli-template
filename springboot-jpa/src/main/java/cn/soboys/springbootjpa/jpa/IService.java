package cn.soboys.springbootjpa.jpa;

import java.io.Serializable;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 09:52
 * @webSite https://github.com/coder-amiao
 */
public interface IService<T, ID> {

    T save(T entity);
}
