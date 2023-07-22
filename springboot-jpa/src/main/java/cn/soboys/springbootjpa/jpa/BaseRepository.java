package cn.soboys.springbootjpa.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.stereotype.Repository;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 09:52
 * @webSite https://github.com/coder-amiao
 * JPA自定义泛型DAO接口封装
 */
@NoRepositoryBean
public interface BaseRepository<T,ID> extends JpaRepository<T,ID> {


}
