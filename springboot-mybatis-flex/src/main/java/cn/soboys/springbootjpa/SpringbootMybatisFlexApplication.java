package cn.soboys.springbootjpa;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


@SpringBootApplication
@EnableRestFullApi
@MapperScan("cn.soboys.springbootjpa.mapper")
public class SpringbootMybatisFlexApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootMybatisFlexApplication.class, args);
    }

}
