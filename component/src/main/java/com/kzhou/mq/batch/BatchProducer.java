package com.kzhou.mq.batch;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
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
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("10.181.124.11:9876");
        //设置最大消息大小，默认4M
        producer.setMaxMessageSize(1024 * 1024 * 4);
        producer.start();

        String topic = "bitch-topic";
        List<Message> messages = new ArrayList<>();
        for (int i=0;i<10000;i++){
            messages.add(new Message(topic,"bitch-topic-tag",("hello rocketmq"+i).getBytes(StandardCharsets.UTF_8)));
        }
        //把大的消息分裂成若干个小的消息
        ListSplitter splitter = new ListSplitter(messages);
        while (splitter.hasNext()) {
            //安装4m切割消息
            List<Message>  listItem = splitter.next();
            //发送消息
            SendResult sendResult = producer.send(listItem);
            System.out.println("发送成功：" + sendResult.getMessageQueue().getQueueId());
        }
    }
}
