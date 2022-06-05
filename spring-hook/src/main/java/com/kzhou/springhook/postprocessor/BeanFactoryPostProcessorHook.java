package com.kzhou.springhook.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * @Description BeanFactoryPostProcessorHook
 *  可以对整个beanFactory实例化对象之前。加载完bean定义之后的属性进行修改
 * @Author zhoukaidong
 * @Date 2022/2/9 10:51
 **/
@Component
public class BeanFactoryPostProcessorHook implements BeanFactoryPostProcessor {

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
