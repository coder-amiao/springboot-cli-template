package cn.soboys.springbootjpa.repository;

import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.jpa.BaseRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/19 12:02
 * @webSite https://github.com/coder-amiao
 * 数据库 dao层。
 */
@Repository
public interface CategoryRepository extends BaseRepository<Category,Long> {

}
