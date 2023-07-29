package cn.soboys.springbootjpa.service;

import cn.soboys.springbootjpa.bean.Category;
import cn.soboys.springbootjpa.mapper.CategoryMapper;
import com.mybatisflex.spring.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/26 22:18
 * @webSite https://github.com/coder-amiao
 */
@Service
public class CategoryServiceImpl extends ServiceImpl<CategoryMapper, Category> implements CategoryService {
}
