package com.kzhou.springhook.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * @Description BeanClassLoaderAware
 * @Author zhoukaidong
 * @Date 2022/2/8 19:06
 **/
@Component
public class SpringHookAware4 implements BeanClassLoaderAware {

    private ClassLoader classLoader;

    public void loadClass(){
        try {
            classLoader.loadClass("com.kzhou.springhook.test.TestLoadClass1");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }
}
