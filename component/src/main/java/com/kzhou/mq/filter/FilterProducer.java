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
        producer.setNamesrvAddr("10.181.124.11：9876");
        producer.start();

        for (int i=0;i<3;i++){
            Message message = new Message("topicFilter","TAG-FILTER",("hello rocketMq" + i).getBytes(StandardCharsets.UTF_8));
            message.putUserProperty("a",String.valueOf(i));
            if(i % 2 ==0){
                message.putUserProperty("b","name1");
            }else {
                message.putUserProperty("b","name2");
            }
            producer.send(message);
        }

        producer.shutdown();
    }
}
