package cn.soboys.springbootjpa.controller;


import cn.soboys.restapispringbootstarter.Result;
import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.service.ICategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;


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


    @GetMapping("/add")
    public Result addCategory() {
        Category category = new Category();
        //category.setTitle("jpa");
        categoryService.save(category);
        return Result.buildSuccess();
    }

}
