package com.kzhou.mq.schedule;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.concurrent.TimeUnit;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 17:24
 **/
public class ScheduleMessageProducer {
    private volatile static boolean flag = true;
    private volatile static int increment = 100;

    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("192.168.0.11:9876");
        producer.start();
        while (flag){
            increment++;
            Message message = new Message("delayTopic","delayTopic","",
                    ("delay topic test" + increment).getBytes(StandardCharsets.UTF_8));
            // 1s 5s 10s 30s 1m 2m 3m 4m 5m 6m 7m 8m 9m 10m 20m 30m 1h 2h
            message.setDelayTimeLevel(4);
            SendResult sendResult = producer.send(message);
            System.out.println("发送成功："+sendResult.getMsgId()+",发送的消息：" + "delay topic test" + increment);

            TimeUnit.SECONDS.sleep(1);
        }

        producer.shutdown();
    }

}
