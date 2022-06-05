package com.kzhou.mq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 15:42
 **/
public class SyncProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("192.168.0.11:9876");
        producer.start();

        Message message = new Message("testTopic","testTopic","property-b",
                "Hello RocketMq".getBytes(StandardCharsets.UTF_8));
        SendResult sendResult = producer.send(message);

        System.out.println("发送成功："+sendResult.getMsgId());

        producer.shutdown();
    }
}
