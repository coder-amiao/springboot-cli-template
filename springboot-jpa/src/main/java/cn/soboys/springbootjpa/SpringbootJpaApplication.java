package cn.soboys.springbootjpa;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import cn.soboys.simplestjpa.annotation.EnableJPAQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableJPAQuery
public class SpringbootJpaApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootJpaApplication.class, args);
    }

}
