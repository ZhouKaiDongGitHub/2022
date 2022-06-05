package com.kzhou.springhook.aware;

import org.springframework.beans.factory.BeanClassLoaderAware;
import org.springframework.context.EnvironmentAware;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;

/**
 * @Description EnvironmentAware
 * @Author zhoukaidong
 * @Date 2022/2/8 19:06
 **/
@Component
public class SpringHookAware5 implements EnvironmentAware {

    private Environment environment;

    public Environment getEnvironment(){
        return environment;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }
}
