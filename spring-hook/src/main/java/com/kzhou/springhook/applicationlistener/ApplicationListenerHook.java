package com.kzhou.springhook.applicationlistener;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * @Description ApplicationListenerHook
 * @Author zhoukaidong
 * @Date 2022/2/9 11:46
 **/
@Component
public class ApplicationListenerHook implements ApplicationListener<EventDemo> {

    @Autowired
    private ApplicationContext applicationContext;

    @Override
    public void onApplicationEvent(EventDemo event) {
        System.out.println("监听到事件："+ event.getAttr1()+event.getAttr2());
    }

}
