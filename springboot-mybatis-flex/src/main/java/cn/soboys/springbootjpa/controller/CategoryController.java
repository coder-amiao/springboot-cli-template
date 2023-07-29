package cn.soboys.springbootjpa.controller;

import cn.soboys.restapispringbootstarter.Result;

import cn.soboys.springbootjpa.bean.Category;
import cn.soboys.springbootjpa.bean.table.CategoryTableDef;
import cn.soboys.springbootjpa.mapper.CategoryMapper;
import com.mybatisflex.core.query.QueryWrapper;
import com.mybatisflex.processor.entity.TableInfo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


import java.util.HashMap;
import java.util.Map;

import static cn.soboys.springbootjpa.bean.table.CategoryTableDef.CATEGORY;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/19 17:13
 * @webSite https://github.com/coder-amiao
 */
@RestController
@RequestMapping("/category")
@Tag(name = "内容分类")
@Validated
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryMapper categoryMapper;


    @GetMapping("/test")
    public Result test() {
        QueryWrapper queryWrapper=new QueryWrapper();
        queryWrapper.select(Category::getTitle).from(Category.class);
        return Result.buildSuccess();
    }

}
