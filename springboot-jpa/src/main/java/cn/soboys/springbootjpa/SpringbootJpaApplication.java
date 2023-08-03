package cn.soboys.springbootjpa;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import cn.soboys.simplestjpa.annotation.EnableJPAQuery;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
@EnableJPAQuery
@Slf4j
public class SpringbootJpaApplication {

    public static void main(String[] args) {
        ApplicationContext ctx= SpringApplication.run(SpringbootJpaApplication.class, args);
        listBeans(ctx);
    }

    public static void listBeans(ApplicationContext ctx) {

        log.info("bean总数:{}", ctx.getBeanDefinitionCount());
        String[] allBeanNames = ctx.getBeanDefinitionNames();
        for (String beanName : allBeanNames) {
            System.out.println(beanName);
        }
    }
}
