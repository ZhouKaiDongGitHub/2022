package com.kzhou.mq.schedule;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 17:24
 **/
public class ScheduleMessageConsumer1 {

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        consumer.setNamesrvAddr("192.168.0.11:9876");
        consumer.subscribe("delayTopic","*");
        consumer.registerMessageListener((MessageListenerConcurrently) (messageExtList, consumeConcurrentlyContext) -> {
            for (MessageExt messageExt : messageExtList) {
                System.out.println("收到消息：" + new String(messageExt.getBody()));
            }
            return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
        });
        consumer.start();
    }
}
