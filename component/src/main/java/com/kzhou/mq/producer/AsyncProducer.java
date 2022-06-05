package com.kzhou.mq.producer;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.concurrent.TimeUnit;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 15:43
 **/
public class AsyncProducer {

    public static volatile boolean flag = true;

    public static void main(String[] args) throws Exception{

        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("192.168.0.11:9876");
        producer.start();
        while (flag){
            String time = String.valueOf(new Date().getTime());
            Message message = new Message("testTopic","testTopic","property-a",
                    ("Hello RocketMq" +time) .getBytes(StandardCharsets.UTF_8));
            producer.send(message, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    //回调成功
                    System.out.println("发送成功：msgId"+ sendResult.getMsgId()
                            + ",queue:" +sendResult.getMessageQueue().getQueueId()
                            +",brokerName:" +sendResult.getMessageQueue().getBrokerName());
                }

                @Override
                public void onException(Throwable throwable) {
                    //回调失败
                    System.err.println("发送失败:错误信息"+ throwable.getMessage());
                }
            });
            TimeUnit.MILLISECONDS.sleep(1000);
        }
        producer.shutdown();
    }
}
