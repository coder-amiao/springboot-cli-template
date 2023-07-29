package cn.soboys.springbootjpa.bean;

import com.mybatisflex.annotation.Id;
import com.mybatisflex.annotation.KeyType;
import com.mybatisflex.annotation.Table;
import lombok.Data;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/26 16:34
 * @webSite https://github.com/coder-amiao
 */
@Data
@Table("cms_category")
public class Category {

    @Id(keyType = KeyType.Auto)
    private Long id;

    private String title;
}
