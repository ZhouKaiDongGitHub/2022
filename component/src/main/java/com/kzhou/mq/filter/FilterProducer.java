package com.kzhou.mq.filter;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 17:01
 **/
public class FilterProducer {


    // 环境需要加上配置信息  ## enablePropertyFilter=true
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("192.168.0.11:9876");
        producer.start();
        String[] tags = new String[]{"filter-topic-tagA","filter-topic-tagB","filter-topic-tagC"};

        for (int i=0;i<30;i++){

            Message message = new Message("filter-topic",tags[i%3],("hello rocketMq" + i).getBytes(StandardCharsets.UTF_8));


            if(i % 3 ==0){
                message.putUserProperty("a","name1");
            }else if(i % 3 ==1){
                message.putUserProperty("a","name2");
            }else {
                message.putUserProperty("a","name3");
            }
            producer.send(message);
        }

        producer.shutdown();
    }
}
