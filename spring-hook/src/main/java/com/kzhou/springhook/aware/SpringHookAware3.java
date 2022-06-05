package com.kzhou.springhook.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description BeanNameAware
 * @Author zhoukaidong
 * @Date 2022/2/8 19:06
 **/
@Component("springHookAware3")
public class SpringHookAware3 implements BeanNameAware {

    private String beanName;

    public String getBeanName(){
        return beanName;
    }


    @Override
    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }
}
