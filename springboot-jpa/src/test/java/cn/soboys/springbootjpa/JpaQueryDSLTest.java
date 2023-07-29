package cn.soboys.springbootjpa;

import cn.soboys.simplestjpa.QueryWrapper;
import cn.soboys.springbootjpa.entity.Category;
import cn.soboys.springbootjpa.entity.QCategory;
import cn.soboys.springbootjpa.entity.QModule;
import cn.soboys.springbootjpa.entity.dto.CategoryDto;
import cn.soboys.springbootjpa.service.ICategoryService;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.extern.slf4j.Slf4j;
import org.dromara.hutool.core.text.StrUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/22 23:21
 * @webSite https://github.com/coder-amiao
 */
@SpringBootTest
@Slf4j
public class JpaQueryDSLTest {

    @Autowired
    private ICategoryService categoryService;

    @Autowired
    private JPAQueryFactory queryWrapper;


    /**
     * select() 和 fetch() 的常用写法
     * 使用fetch()查询时，数据库没有符合该条件的数据时，返回的是空集合，而不是null
     */

    /**
     * 查询字段-select()
     */
    @Test
    public void fetchColum() {
        QCategory qCategory = QCategory.category;
        List<String> a = queryWrapper
                .select(qCategory.title)
                .from(qCategory)
                .fetch();
        log.info("返回数量{}", a.size());
    }

    /**
     * 查询实体-selectFrom()
     */
    @Test
    public void fetchEntity() {
        QCategory qCategory = QCategory.category;
        List<Category> categories = queryWrapper.selectFrom(qCategory).fetch();
        log.info("返回数量{}", categories.size());

    }

    /**
     * 查询并将结果封装至dto中
     */
    @Test
    public void fetchDto() {
        QCategory qCategory = QCategory.category;
        List<CategoryDto> categoryDtos = queryWrapper.select(
                        Projections.bean(CategoryDto.class, qCategory.title)
                )
                .from(qCategory).fetch();
        log.info("返回数量{}", categoryDtos.size());

    }

    /**
     * 去重查询-selectDistinct()
     */
    @Test
    public void fetchDistinct() {
        QCategory qCategory = QCategory.category;
        List<String> c = queryWrapper
                .selectDistinct(qCategory.title)
                .from(qCategory)
                .fetch();
        log.info("返回数量{}", c.size());
    }

    /**
     * 获取首个查询结果-fetchFirst() 单条记录 limit 1
     */
    @Test
    public void fetchFirst() {
        QCategory qCategory = QCategory.category;
        Category category = queryWrapper
                .selectFrom(qCategory)
                .fetchFirst();
        log.info("返回数量{}", category.toString());
    }

    /**
     * 获取唯一查询结果-fetchOne()
     * 当fetchOne()根据查询条件从数据库中查询到多条匹配数据时，会抛`NonUniqueResultException`
     */
    @Test
    public void fetchOne() {
        QCategory qCategory = QCategory.category;
        Category category = queryWrapper
                .selectFrom(qCategory)
                .fetchOne();
        log.info("返回数量{}", category.toString());
    }


    /**
     * where 子句查询条件的常用写法
     */
    @Test
    public void fetchWhere() {
        QCategory qCategory = QCategory.category;
        List<Category> categories = queryWrapper
                .selectFrom(qCategory)
                .where(qCategory.title.eq("更新")
                        .and(qCategory.subTitle.like('%' + "测试" + '%')))
                .fetch();
        log.info("返回数量{}", categories.size());

    }

    /**
     * where 动态条件查询
     */

    /**
     * 使用QueryDSL提供的BooleanBuilder来进行查询条件管理。
     */
    @Test
    public void fetchWhereDynamic() {
        QCategory qCategory = QCategory.category;
        BooleanBuilder builder = new BooleanBuilder();
        String title = "a";
        if (StrUtil.isNotEmpty(title)) {
            builder.and(qCategory.title.eq(title));
        }
        String subTitle = "";
        if (StrUtil.isNotEmpty(subTitle)) {
            builder.and(qCategory.subTitle.eq(subTitle));
        }
        List<Category> categories = queryWrapper
                .selectFrom(qCategory)
                .where(builder)
                .fetch();
        log.info("返回数量{}", categories.size());

    }

    /**
     * 复杂的查询关系
     */
    @Test
    public void fetchWhereDynamicComplex() {
        QCategory qCategory = QCategory.category;

        BooleanBuilder builder = new BooleanBuilder();
        builder.or(qCategory.id.eq(1l));

        String title = "a";
        if (StrUtil.isNotEmpty(title)) {
            builder.and(qCategory.title.eq(title));
        }
        String subTitle = "";
        if (StrUtil.isNotEmpty(subTitle)) {
            builder.and(qCategory.subTitle.eq(subTitle));
        }


        List<Category> categories = queryWrapper
                .selectFrom(qCategory)
                .where(builder)
                .fetch();
        log.info("返回数量{}", categories.size());
    }


    /**
     * 自定义封装查询的结果集
     * JPAQueryFactory查询工厂的select方法可以将Projections方法返回的QBean作为参数，通过Projections的bean方法来构建返回的结果集映射到实体内，有点像Mybatis内的ResultMap的形式，不过内部处理机制肯定是有着巨大差别的！
     * <p>
     * bean方法第一个参数需要传递一个实体的泛型类型作为返回集合内的单个对象类型，如果QueryDSL查询实体内的字段与DTO实体的字段名字不一样时，可以采用as方法来处理，为查询的结果集指定的字段添加别名，这样就会自动映射到DTO实体内。
     */

    /**
     * 使用Projections的Bean方法
     */
    @Test
    public void fetchBean() {
        QCategory qCategory = QCategory.category;
        QModule qModule = QModule.module;

        List<CategoryDto> categoryDtos = queryWrapper
                .select(
                        Projections.bean(CategoryDto.class
                                , qCategory.title, qModule.code)
                ).from(qCategory, qModule).fetch();
        log.info("返回数量{}", categoryDtos.size());
    }

    /**
     * 使用Projections的fields方法
     */
    @Test
    public void fetchFields() {
        QCategory qCategory = QCategory.category;
        List<CategoryDto> categoryDtos = queryWrapper
                .select(
                        Projections.fields(CategoryDto.class
                                , qCategory.title)
                ).from(qCategory).fetch();
        log.info("返回数量{}", categoryDtos.size());
    }

    /**
     * 使用集合的stream转换
     * 从方法开始到fetch()结束完全跟QueryDSL没有任何区别，采用了最原始的方式进行返回结果集，但是从fetch()获取到结果集后处理的方式就有所改变了。
     * <p>
     * fetch()方法返回的类型是泛型List（List），List继承了Collection，完全存在使用Collection内非私有方法的权限，通过调用stream方法可以将集合转换成Stream泛型对象，该对象的map方法可以操作集合内单个对象的转换，具体的转换代码可以根据业务逻辑进行编写。
     * <p>
     * 在map方法内有个lambda表达式参数tuple，通过tuple对象get方法就可以获取对应select方法内的查询字段。
     * ————————————————
     */
    @Test
    public void selectWithStream() {
        QCategory qCategory = QCategory.category;
        List<CategoryDto> categoryDtos = queryWrapper
                .select(qCategory.title, qCategory.subTitle)
                .from(qCategory)
                .fetch().stream().map(tuple -> {
                    CategoryDto c = new CategoryDto();
                    c.setTitle(tuple.get(qCategory.title));
                    return c;
                }).collect(Collectors.toList());

        log.info("返回数量{}", categoryDtos.size());
    }


    @Test
    public void findByQuery() {
        QCategory qCategory = QCategory.category;
        //该Predicate为querydsl下的类,支持嵌套组装复杂查询条件
        BooleanBuilder builder = new BooleanBuilder();
        String title = "a";
        if (StrUtil.isNotEmpty(title)) {
            builder.and(qCategory.title.eq(title));
        }
        String subTitle = "";
        if (StrUtil.isNotEmpty(subTitle)) {
            builder.and(qCategory.subTitle.eq(subTitle));
        }
        List<Category> c = categoryService.list(builder);
       log.info("条数{}",c.size());
    }

    @Test
    public void findByQueryWrapper(){
        QCategory qCategory = QCategory.category;
        JPAQueryFactory queryWrapper=QueryWrapper.of();
        List<String> c = queryWrapper
                .selectDistinct(qCategory.title)
                .from(qCategory)
                .fetch();
        log.info("返回数量{}", c.size());
    }


}
