package com.kzhou.mq.batch;

import io.netty.util.CharsetUtil;
import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.consumer.ConsumeFromWhere;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/5 10:26
 **/
public class BitchConsumer {

    public static void main(String[] args) throws MQClientException {
        //创建消费者
        DefaultMQPushConsumer defaultMQPushConsumer = new DefaultMQPushConsumer("consumer-group");
        //设置name server 地址
        defaultMQPushConsumer.setNamesrvAddr("192.168.0.11:9876");
        //从开始位置消费
        defaultMQPushConsumer.setConsumeFromWhere(ConsumeFromWhere.CONSUME_FROM_FIRST_OFFSET);
        //批量拉取消息数量，默认32
        defaultMQPushConsumer.setPullBatchSize(32);
        //每次消费条数，默认1
        defaultMQPushConsumer.setConsumeMessageBatchMaxSize(10);

        //订阅
        defaultMQPushConsumer.subscribe("bitch-topic","bitch-topic-tag");

        defaultMQPushConsumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                list.forEach(message->{
                    System.out.println(message+" ; "+new String(message.getBody(), CharsetUtil.UTF_8));
                });

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        defaultMQPushConsumer.start();
    }
}
