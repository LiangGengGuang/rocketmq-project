package com.example.rocketmq.transaction;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;

/**
 * @author LiangGengguang
 * @create 2020-03-03 00:10
 */
public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        DefaultMQProducer producer = new DefaultMQProducer("transaction_producer");

        producer.setNamesrvAddr("localhost:19876;localhost:29876");
        producer.start();

        for (int i = 0; i < 10; i++) {

            Message msg = new Message("TopicTest", "TagA", ("transaction message" + i).getBytes());

            SendResult send = producer.send(msg);
            System.out.println(send);
        }

        System.out.println("发送完成");
        producer.shutdown();
    }
}
