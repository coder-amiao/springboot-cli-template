package cn.soboys.springbootjpa.controller;

import cn.soboys.restapispringbootstarter.Result;
import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

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

    private final ICategoryService categoryService;

    @PostMapping("/add")
    @Operation(summary = "添加内容分类")
    public Category add(@RequestBody @Validated Category category) {
        return categoryService.save(category);
    }

    @GetMapping("/test")
    public Result t(){
        Map m=new HashMap<>();
        m.put("double",20.899);
        return Result.buildSuccess(m);
    }

}
