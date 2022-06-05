package com.kzhou.springhook.postprocessor;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description BeanPostProcessorHook
 *      对具体的某一个bean进行实例化前的处理
 * @Author zhouKaiDong
 * @Date 2022/2/9 10:54
 **/
@Component
public class BeanPostProcessorHook implements BeanPostProcessor {

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
       if(beanName.equals("")){

       }
       return bean;
    }

}
