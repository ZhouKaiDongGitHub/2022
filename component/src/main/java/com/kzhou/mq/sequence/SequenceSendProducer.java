package com.kzhou.mq.sequence;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 23:55
 **/
public class SequenceSendProducer {
    public static void main(String[] args) throws Exception{
        DefaultMQProducer producer = new DefaultMQProducer("producer-group");
        producer.setNamesrvAddr("192.168.0.11:9876");
        producer.start();

        List<OrderInfoDto> orderInfoDtoList = new ArrayList<>();
        buildOrderInfo(orderInfoDtoList);

        for(int i=0; i<10 ;i++){
            // 同一类型的消息，放到同一个queue中去
            String body = orderInfoDtoList.get(i).toString();
            Message message = new Message("orderTopic","orderTopic","orderTopic",
                    body.getBytes(StandardCharsets.UTF_8));
            SendResult sendResult = producer.send(message, new MessageQueueSelector() {
                @Override
                public MessageQueue select(List<MessageQueue> mqs, Message msg, Object arg) {
                    System.out.println("broker-a的queue的数量为：" + mqs.size());
                    long orderId = (Long) (arg);
                    long index = orderId % mqs.size();
                    return mqs.get((int) index);
                }
            }, orderInfoDtoList.get(i).getOrderId());
            System.out.println("发送成功>>>发送的消息为:"+ body +",发送到的Queue为:" + sendResult.getMessageQueue().getQueueId());
        }

        producer.shutdown();
    }

    private static void buildOrderInfo(List<OrderInfoDto> orderInfoDtoList){
        orderInfoDtoList.add(new OrderInfoDto(20220605001L,"创建订单","2022-06-05 12:20:20"));
        orderInfoDtoList.add(new OrderInfoDto(20220605002L,"创建订单","2022-06-05 12:20:21"));
        orderInfoDtoList.add(new OrderInfoDto(20220605003L,"创建订单","2022-06-05 12:20:22"));
        orderInfoDtoList.add(new OrderInfoDto(20220605004L,"创建订单","2022-06-05 12:20:23"));
        orderInfoDtoList.add(new OrderInfoDto(20220605001L,"减库存","2022-06-05 12:21:20"));
        orderInfoDtoList.add(new OrderInfoDto(20220605002L,"减库存","2022-06-05 12:21:21"));
        orderInfoDtoList.add(new OrderInfoDto(20220605003L,"减库存","2022-06-05 12:21:22"));
        orderInfoDtoList.add(new OrderInfoDto(20220605004L,"减库存","2022-06-05 12:21:23"));
        orderInfoDtoList.add(new OrderInfoDto(20220605001L,"支付费用","2022-06-05 12:22:20"));
        orderInfoDtoList.add(new OrderInfoDto(20220605002L,"支付费用","2022-06-05 12:22:21"));
    }
}
