package com.kzhou.spring.boot;

import com.kzhou.spring.boot.controller.TestController;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/3/18 11:41
 **/
@SpringBootApplication
public class Application {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(Application.class);
        TestController bean = context.getBean(TestController.class);
        bean.run();
    }
}
