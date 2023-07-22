package cn.soboys.springbootjpa.service.impl;

import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.jpa.ServiceImpl;
import cn.soboys.springbootjpa.repository.CategoryRepository;
import cn.soboys.springbootjpa.service.ICategoryService;
import org.springframework.stereotype.Service;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/20 14:46
 * @webSite https://github.com/coder-amiao
 */
@Service
public class CategoryServerImpl extends ServiceImpl<CategoryRepository, Category, Long> implements ICategoryService {


}
