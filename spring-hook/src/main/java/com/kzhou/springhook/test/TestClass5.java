package com.kzhou.springhook.test;

import org.springframework.beans.factory.annotation.Autowired;

/**
 * @Description TestClass4
 * @Author zhoukaidong
 * @Date 2022/2/8 19:14
 **/
public class TestClass5 {

    private String name;

    @Autowired
    private TestClass1 testClass1;

    public void call(){
        System.out.println("TestClass5 call");
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
