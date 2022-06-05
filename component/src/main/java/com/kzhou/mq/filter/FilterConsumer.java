package com.kzhou.mq.filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 17:00
 **/
public class FilterConsumer {

    public static void main(String[] args) throws Exception{
        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("consumer-group");
        consumer.setNamesrvAddr("192.168.0.11:9876");
        consumer.subscribe("filter-topic", MessageSelector.byTag("filter-topic-tagA"));
        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> messageExtList, ConsumeConcurrentlyContext consumeConcurrentlyContext) {
                for (MessageExt messageExt : messageExtList) {
                    messageExt.getQueueId();
                    messageExt.getBody().toString();
                }
                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });
        consumer.start();
    }
}
