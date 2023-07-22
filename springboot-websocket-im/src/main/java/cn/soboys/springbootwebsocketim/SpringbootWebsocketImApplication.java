package cn.soboys.springbootwebsocketim;

import cn.soboys.restapispringbootstarter.annotation.EnableRestFullApi;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableRestFullApi
public class SpringbootWebsocketImApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringbootWebsocketImApplication.class, args);
    }

}
