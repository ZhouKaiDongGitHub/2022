package com.kzhou.springhook;

import com.kzhou.springhook.applicationlistener.ApplicationListenerService;
import com.kzhou.springhook.aware.*;
import com.kzhou.springhook.factorybean.FactoryBeanHook;
import com.kzhou.springhook.factorybean.UserMapper;
import com.kzhou.springhook.importhook.ImportBeanDefinitionRegistrarHook1;
import com.kzhou.springhook.importhook.ImportSelectorHook;
import com.kzhou.springhook.test.*;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.Environment;

import java.lang.reflect.Proxy;

/**
 * @Description Application启动
 * @Author zhoukaidong
 * @Date 2022/2/8 19:09
 **/
@Configuration
@ComponentScan("com.kzhou.springhook")
@Import({ImportBeanDefinitionRegistrarHook1.class, ImportSelectorHook.class})
public class Application {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(Application.class);
        applicationContext.refresh();
        applicationContext.start();

         TestClass1 testClass1 = applicationContext.getBean(TestClass1.class);
         testClass1.call();

        SpringHookAware1 springHookAware1 = applicationContext.getBean(SpringHookAware1.class);
        TestClass2 testClass2 = (TestClass2)springHookAware1.getBeanByName("testClass2");
        testClass2.call();

        SpringHookAware2 springHookAware2 = applicationContext.getBean(SpringHookAware2.class);
        TestClass2 testClass22 = (TestClass2)springHookAware2.getBeanByName("testClass2");
        testClass22.call();

        SpringHookAware3 springHookAware3 = applicationContext.getBean(SpringHookAware3.class);
        System.out.println(springHookAware3.getBeanName());

        SpringHookAware4 springHookAware4 = applicationContext.getBean(SpringHookAware4.class);
         // springHookAware4.loadClass(); System.out.println(applicationContext.getBean("testLoadClass1"));

        SpringHookAware5 springHookAware5 = applicationContext.getBean(SpringHookAware5.class);
        Environment environment = springHookAware5.getEnvironment();
        System.out.println(environment.getDefaultProfiles()[0]);
        System.out.println(environment.getActiveProfiles().length);

        TestClass3 testClass3 = applicationContext.getBean(TestClass3.class);
        testClass3.call();

        TestClass4 testClass4 = applicationContext.getBean(TestClass4.class);
        testClass4.call();

        TestClass5 testClass5 = applicationContext.getBean(TestClass5.class);
        System.out.println(testClass5.getName());

        UserMapper userMapper = (UserMapper)applicationContext.getBean("factoryBeanHook");
        System.out.println(userMapper.testUserMapper());

        ApplicationListenerService applicationListenerService = applicationContext.getBean(ApplicationListenerService.class);
        applicationListenerService.sendEvent();
    }

}
