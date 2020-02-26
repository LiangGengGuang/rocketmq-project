package com.example.rocketmq.order;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.MessageQueueSelector;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageQueue;

import java.util.List;

/**
 * @author LiangGengguang
 * @create 2020-02-24 16:31
 */
public class OrderProducer {

    public static void main(String[] args) throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("order_producer");
        producer.setNamesrvAddr("localhost:19876;localhost:29876");
        producer.start();

        String[] tags = new String[]{"TagA", "TagB", "TagC", "TagD", "TagE"};

        for (int i = 0; i < 10; i++) {

            int orderId = i % 10;
            Message msg = new Message("TopicTest", tags[i % tags.length], "KEY" + i, ("Hello order_message " + i).getBytes());

            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {

                @Override
                public MessageQueue select(List<MessageQueue> msglist, Message msg, Object o) {
                    Integer id = (Integer) o;
                    int index = id % msglist.size();
                    return msglist.get(index);
                }
            }, orderId);

            System.out.printf("%s%n", sendResult);
        }
        producer.shutdown();
    }
}
