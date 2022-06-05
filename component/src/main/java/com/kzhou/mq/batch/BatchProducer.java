package com.kzhou.mq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 16:52
 **/
public class BatchProducer {

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("test-producer");
        producer.setNamesrvAddr("10.181.124.11:9876");
        producer.start();

        String topic = "test-bitch";
        List<Message> messageList = new ArrayList<>();
        messageList.add(new Message(topic,"tagA","orderId","hello rocketmq1".getBytes(StandardCharsets.UTF_8)));
        messageList.add(new Message(topic,"tagB","orderId","hello rocketmq2".getBytes(StandardCharsets.UTF_8)));
        messageList.add(new Message(topic,"tagC","orderId","hello rocketmq3".getBytes(StandardCharsets.UTF_8)));

        // 建议将消息分4M一下一次发送

    }
}
