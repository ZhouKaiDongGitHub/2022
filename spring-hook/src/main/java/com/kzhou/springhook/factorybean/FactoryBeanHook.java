package com.kzhou.springhook.factorybean;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * @Description FactoryBeanHook
 *     实例化一个bean。注册到spring中，在调用的时候进行初始化具体方法
 *     一般使用场景有2个 一个是太复杂的bean自己进行实例化
 *     另一个是配置动态代理实现特殊的功能
 * @Author zhoukaidong
 * @Date 2022/2/9 11:11
 **/
@Component
public class FactoryBeanHook implements FactoryBean {

    @Override
    public Object getObject() throws Exception {
        Object proxyInstance = Proxy.newProxyInstance(ClassLoader.getSystemClassLoader(), new Class[]{UserMapper.class}, new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                System.out.println(proxy instanceof UserMapper);
                Select annotation = method.getAnnotation(Select.class);
                return annotation.value();
            }
        });
        return proxyInstance;
    }

    @Override
    public Class<?> getObjectType() {
        return null;
    }

}
