package cn.soboys.springbootjpa.entity;

import cn.soboys.springbootjpa.entity.base.BaseEntity;
import lombok.Data;

import javax.persistence.*;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/19 16:25
 * @webSite https://github.com/coder-amiao
 * 文章
 */
@Data
@Entity
@Table(name = "cms_article")
public class Article extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;


}
