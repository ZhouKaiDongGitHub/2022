package com.kzhou.springhook.aware;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description ApplicationContextAware
 * @Author zhouKaiDong
 * @Date 2022/2/8 19:06
 **/
@Component
public class SpringHookAware1 implements ApplicationContextAware {

    private ApplicationContext applicationContext;

    public Object getBeanByName(String beanName){
        Object bean = applicationContext.getBean(beanName);
        return bean;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
}
