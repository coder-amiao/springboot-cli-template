package cn.soboys.springbootjpa;


import cn.soboys.springbootjpa.bean.Category;
import cn.soboys.springbootjpa.mapper.CategoryMapper;
import cn.soboys.springbootjpa.service.CategoryService;
import com.mybatisflex.core.query.QueryColumn;
import com.mybatisflex.core.query.QueryCondition;
import com.mybatisflex.core.query.QueryWrapper;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/22 23:21
 * @webSite https://github.com/coder-amiao
 */
@SpringBootTest
@Slf4j
public class JpaTest {

    @Autowired(required = false)
    private CategoryService categoryService;

    @Test
    void countByCondition(){
    QueryWrapper queryWrapper=QueryWrapper.create();
    queryWrapper
            .select(Category::getTitle,Category::getId).from(Category.class);
    long size= categoryService.list(queryWrapper).size();
     log.info("返回条数{}",queryWrapper.toSQL());

}
}
