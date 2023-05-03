package cn.soboys.springbootrestfulapi.controller;

import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/29 23:40
 * @webSite https://github.com/coder-amiao
 */
@Data
public class Student {

    private String userName;
    private Integer age;
    private Double balance;
    private String idCard;
    private List hobby;
    private Map other;
}
