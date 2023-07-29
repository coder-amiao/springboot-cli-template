package cn.soboys.springbootjpa.entity.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/7/28 21:40
 * @webSite https://github.com/coder-amiao
 */
@Getter
@Setter
public class CategoryDto {

    /**
     * 子标题
     */
    @Column(length = 128)
    @Schema(description = "子标题")
    private String title;
}
