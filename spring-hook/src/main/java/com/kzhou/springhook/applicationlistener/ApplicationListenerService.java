package com.kzhou.springhook.applicationlistener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

/**
 * @Description ApplicationListenerService
 * @Author zhoukaidong
 * @Date 2022/2/9 18:29
 **/
@Component
public class ApplicationListenerService {

    @Autowired
    private ApplicationContext applicationContext;

    public void sendEvent(){
        EventDemo eventDemo = new EventDemo("event");
        eventDemo.setAttr1("attr1");
        eventDemo.setAttr2("attr2");
        applicationContext.publishEvent(eventDemo);
    }
}
