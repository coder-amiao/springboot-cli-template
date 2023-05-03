package cn.soboys.springbootrestfulapi.controller;


import cn.soboys.springbootrestfulapi.common.resp.R;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


import javax.validation.constraints.NotBlank;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 公众号 程序员三时
 * @version 1.0
 * @date 2023/4/28 23:58
 * @webSite https://github.com/coder-amiao
 */
@RestController
@Validated
public class IndexController {

    @GetMapping("/index")
    public R index(@NotBlank(message = "用户名不能为空") String  userName) {
        Map m = new HashMap();
        m.put("name", "Tom");
        m.put("age", 25);
        m.put("sex", "男");
        return R.success().data(m);
    }


    @GetMapping("/home")
    public R home() {

        Student s = new Student();
        s.setUserName("Tom");
        s.setAge(22);
        List hobby = new ArrayList();
        hobby.add("抽烟");
        hobby.add("喝酒");
        hobby.add("烫头");
        s.setHobby(hobby);
        s.setBalance(2229891.0892);
        s.setIdCard("420222199811207237");

        return R.success().data("item", s).message("查询用户详情信息");
    }

    /**
     * 异常返回模拟
     *
     * @return
     */
    @GetMapping("/exception")
    public R exception() {
        int a=4/0;
        return R.success().data("user", a).message("查询用户详情信息");
    }
}
