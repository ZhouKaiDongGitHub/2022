package com.kzhou.springhook.applicationlistener;

import org.springframework.context.ApplicationEvent;

/**
 * @Description EventDemo
 * @Author zhoukaidong
 * @Date 2022/2/9 11:46
 **/
public class EventDemo extends ApplicationEvent {
    private String attr1;
    private String attr2;

    public EventDemo(Object source) {
        super(source);
    }

    public String getAttr1() {
        return attr1;
    }

    public void setAttr1(String attr1) {
        this.attr1 = attr1;
    }

    public String getAttr2() {
        return attr2;
    }

    public void setAttr2(String attr2) {
        this.attr2 = attr2;
    }
}
