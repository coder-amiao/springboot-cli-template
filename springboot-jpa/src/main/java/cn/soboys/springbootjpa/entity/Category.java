package cn.soboys.springbootjpa.entity;

import cn.soboys.springbootjpa.entity.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/19 10:44
 * @webSite https://github.com/coder-amiao
 * 内容分类
 */
@Data
@Entity
@Table(name = "cms_category")
public class Category extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 标题
     */
    @Column(nullable = false, length = 64)
    @Schema(description = "标题")
    private String title;

    /**
     * 子标题
     */
    @Column(length = 128)
    @Schema(description = "子标题")
    private String subTitle;


    /**
     * 描述
     */
    @Schema(description = "描述")
    private String described;

    /**
     * 图标
     */
    @Column( length = 32)
    @Schema(description = "图标",maxLength = 32)
    private String icon;

    /**
     * 图片
     */
    @Column( length = 32)
    @Schema(description = "图片",maxLength = 32)
    private String pic;

    /***
     * 引用关系不填写。默认对应主键。
     */
    @ManyToMany(cascade = {CascadeType.PERSIST, CascadeType.MERGE},fetch = FetchType.LAZY)
    @JoinTable(name = "cms_module_relation",
            joinColumns = @JoinColumn(name = "resource_id"),
            inverseJoinColumns = @JoinColumn(name = "module_id"))
    private Set<Module> modules=new HashSet<>();


}
