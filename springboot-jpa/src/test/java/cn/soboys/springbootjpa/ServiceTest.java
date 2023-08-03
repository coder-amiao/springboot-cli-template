package cn.soboys.springbootjpa;

import cn.soboys.simplestjpa.UpdateWrapper;
import cn.soboys.simplestjpa.plugin.CustomTenant;
import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.entity.QCategory;
import cn.soboys.springbootjpa.entity.dto.QTitleVo;
import cn.soboys.springbootjpa.service.ICategoryService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Expression;
import com.querydsl.core.types.Predicate;
import com.querydsl.jpa.impl.JPAUpdateClause;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/26 21:54
 * @webSite https://github.com/coder-amiao
 */
@SpringBootTest
@Slf4j
public class ServiceTest {
    @Autowired
    private ICategoryService categoryService;

    @Test
    void countByExample() {
        CustomTenant.withoutTenantCondition=true; //自定义不设置租户拦截
        Category category = new Category();
        category.setTitle("测试");
        long count = categoryService.count(Example.of(category));
        log.info("条件count{}", count);
    }

    @Test
    void getById() {
        Optional<Category> category = categoryService.getByIdOpt(6l);
        if (category.isPresent()) {
            log.info(category.get().toString());
        }
    }

    @Test
    void getOne() {
        QCategory qCategory = QCategory.category;
        QTitleVo vo = QTitleVo.titleVo;
        Predicate query = vo.subTitle.eq("batch1");
        Category category = categoryService.getOne(query);
        log.info(category.toString());
    }

    @Test
    void getPageQuery() {
        QCategory qCategory = QCategory.category;
        PageRequest pageRequest = PageRequest.of(0, 20); //第一页从零开始
        Predicate query = qCategory.title.like("%" + "batch" + "%");
        Page<Category> categoryList = categoryService.page(pageRequest, query);
        log.info("数量{}", categoryList.getContent().size());
    }

    @Test
    void getPage() {
        QCategory qCategory = QCategory.category;

        // categoryService.getJPAQueryFactory().select().where(qCategory.)
    }


    @Test
    void save() {
        Category c = new Category();
        c.setTitle("6niub");
        categoryService.save(c);
    }

    @Test
    void deleteById() {
        categoryService.removeById(6l);
    }

    @Test
    void deleteAll() {
        List<Long> ids = new ArrayList<>();
        ids.add(6l);
        ids.add(7l);
        Boolean flag = categoryService.removeByIds(ids);
    }

    /**
     * 实体ID对应存在更新否则添加
     */
    @Test
    void saveOrUpdate() {
        Category c = new Category();
        // c.setTitle("保存");
        categoryService.saveOrUpdate(c);
    }


    @Test
    @Rollback(value = false)
    @Transactional
    void updateChain() {
        QCategory qCategory = QCategory.category;
        categoryService.updateChain(qCategory)
                .set(qCategory.title, "测试jpa")
                .where(qCategory.id.eq(6l)).execute();
    }


    @Test
    @Rollback(value = false)
    @Transactional
    void updateChainRaw() {
        QCategory qCategory = QCategory.category;
        categoryService.updateChain(qCategory)
                .set(qCategory.title, qCategory.title + "append")
                .where(qCategory.id.eq(8l)).execute();
    }

    @Test
    @Rollback(value = false)
    @Transactional
    void update() {
        QCategory qCategory = QCategory.category;
        JPAUpdateClause updateWrapper = UpdateWrapper.of(qCategory);
        updateWrapper.set(qCategory.title, "bh").where(qCategory.id.eq(6l));
        Boolean flag = categoryService.update(updateWrapper);
        log.info("更新{}", flag);
    }

    @Test
    @Rollback(value = false)
    @Transactional
    void updateIgnoreNull() {
        Category category = new Category();
        category.setId(6l);
        // category.setSubTitle("忽略");
        //Category category1 = categoryService.update(category, true);  //会自动忽略实体空属性。

        //category.setTitle("");
        Category category1 = categoryService.update(category, true, new String[]{"title"});  //自定义不忽略字段，
        log.info("更新{}", category1);
    }

    @Test
    void selectQueryChain() {
        QCategory qCategory = QCategory.category;
        List<String> categoryList = categoryService.queryChain()
                .select(qCategory.title)
                .from(qCategory).fetch();
        log.info("返回条数{}", categoryList.size());
    }

    @Test
    void selectQueryChainWhere() {
        QCategory qCategory = QCategory.category;
        List<String> categoryList = categoryService.queryChain()
                .select(qCategory.title)
                .from(qCategory)
                .where(qCategory.id.eq(1l))
                .limit(1)
                .fetch();
        log.info("返回条数{}", categoryList.size());
    }


    @Test
    void selectQuery() {
        QCategory qCategory = QCategory.category;
        BooleanBuilder booleanBuilder = new BooleanBuilder();

        String subTitle = "88";
        if (StrUtil.isNotEmpty(subTitle)) {
            booleanBuilder.and(qCategory.described.eq("88"));
        }
        long id = 6l;
        if (!StrUtil.isBlankIfStr(id)) {
            booleanBuilder.and(qCategory.id.eq(6l));
        }
        List<Category> categories = categoryService.list(booleanBuilder);
        log.info("返回条数{}", categories.size());
    }

}
