package com.kzhou.springhook.initialize.beandefinitionpostprocessor;

import com.kzhou.springhook.test.TestClass5;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.stereotype.Component;

/**
 * @Description BeanDefinitionPostProcessorHook
 *      既可以在操作beanDefinitionMap也可以处理defaultListableBeanFactory
 * @Author zhoukaidong
 * @Date 2022/2/9 10:58
 **/
@Component
public class BeanDefinitionPostProcessorHook implements BeanDefinitionRegistryPostProcessor {

    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(TestClass5.class).getBeanDefinition();
        beanDefinitionRegistry.registerBeanDefinition(beanDefinition.getBeanClassName(),beanDefinition);
    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition beanDefinition = beanFactory.getBeanDefinition(TestClass5.class.getName());
        MutablePropertyValues propertyValues = beanDefinition.getPropertyValues();
        propertyValues.add("name","张三");
    }
}
