package com.kzhou.springhook.initialize;

import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

/**
 * @Description 注解>接口>xml文件
 * @Author zhoukaidong
 * @Date 2022/2/9 10:03
 **/
@Component
public class SpringInitialBeanHook implements InitializingBean, DisposableBean {

    SpringInitialBeanHook(){
        System.out.println("1.构造方法");
    }

    @Override
    public void destroy() throws Exception {
        System.out.println("4. DisposableBean");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        System.out.println("3. InitializingBean");
    }

    @PostConstruct
    public void init(){
        System.out.println("2. @PostConstruct");
    }

    @PreDestroy
    public void close(){
        System.out.println("5. @PreDestroy");
    }
}
