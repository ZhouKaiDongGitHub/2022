package com.kzhou.springhook.aware;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Description BeanFactoryAware
 * @Author zhoukaidong
 * @Date 2022/2/8 19:06
 **/
@Component
public class SpringHookAware2 implements BeanFactoryAware {

    private BeanFactory beanFactory;

    public Object getBeanByName(String beanName){
        Object bean = beanFactory.getBean(beanName);
        return bean;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }
}
