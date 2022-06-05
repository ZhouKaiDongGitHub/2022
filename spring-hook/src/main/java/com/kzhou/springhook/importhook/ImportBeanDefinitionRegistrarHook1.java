package com.kzhou.springhook.importhook;

import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionBuilder;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * @Description ImportBeanDefinitionRegistrarHook1
 *              用于向未初始化的beanDefinitions中添加源bean
 * @Author zhoukaidong
 * @Date 2022/2/9 10:14
 **/
public class ImportBeanDefinitionRegistrarHook1 implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        try {
            Class<?> aClass = Class.forName("com.kzhou.springhook.test.TestClass3");
            BeanDefinition beanDefinition = BeanDefinitionBuilder.genericBeanDefinition(aClass).getBeanDefinition();
            registry.registerBeanDefinition(beanDefinition.getBeanClassName(),beanDefinition);

        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
}
