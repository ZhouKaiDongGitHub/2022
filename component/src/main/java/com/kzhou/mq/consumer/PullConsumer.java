package com.kzhou.mq.consumer;

import org.apache.rocketmq.client.consumer.DefaultMQPullConsumer;
import org.apache.rocketmq.client.consumer.PullResult;
import org.apache.rocketmq.common.message.MessageExt;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @Description 类的作用描述
 * @Author ZhouKaiDong
 * @Date 2022/6/4 15:50
 **/
public class PullConsumer {

    private static final Map<MessageQueue,Long> offsetTable = new HashMap<>();

    public static void main(String[] args) throws Exception{
        DefaultMQPullConsumer consumer = new DefaultMQPullConsumer("consumer-group");
        consumer.setNamesrvAddr("10.181.124.11:9876");
        consumer.start();
        Set<MessageQueue> messageQueues = consumer.fetchSubscribeMessageQueues("test-topic");
        for (MessageQueue messageQueue : messageQueues) {
            while (true){
                PullResult pullResult = consumer.pullBlockIfNotFound(messageQueue, "", getMessageQueueOffset(messageQueue), 0);
                setMessageQueueOffset(messageQueue,pullResult.getNextBeginOffset());
                switch (pullResult.getPullStatus()){
                    case FOUND:
                        List<MessageExt> msgFoundList = pullResult.getMsgFoundList();
                        for (MessageExt messageExt : msgFoundList) {
                            System.out.println(new String(messageExt.getBody()));
                        }
                        break;
                    case NO_MATCHED_MSG:
                    case OFFSET_ILLEGAL:
                        break;
                }
            }
        }
    }

    public static long getMessageQueueOffset(MessageQueue messageQueue){
        Long offset = offsetTable.get(messageQueue);
        if(offset!=null){
            return offset;
        }
        return 0;
    }

    private static void setMessageQueueOffset(MessageQueue messageQueue,long offset){
        offsetTable.put(messageQueue,offset);
    }
}
