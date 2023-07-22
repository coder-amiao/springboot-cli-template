package cn.soboys.springbootjpa.entity;

import cn.soboys.springbootjpa.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/19 14:24
 * @webSite https://github.com/coder-amiao
 * 内容模块
 */
@Data
@Entity
@Table(name = "cms_module")
public class Module extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    /**
     * 业务编码
     */
    @Column( unique = true, nullable = false, length = 32)
    private String code;

    /**
     * 标题
     */
    @Column( nullable = false, length = 64)
    private String title;

    /**
     * 描述
     */
    private String described;

    @ManyToMany(mappedBy = "modules",fetch = FetchType.LAZY)
    private Set<Category> categories=new HashSet<>();


}
