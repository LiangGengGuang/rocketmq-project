package com.example.rocketmq.broadcast;

import org.apache.rocketmq.client.exception.MQBrokerException;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.exception.RemotingException;

/**
 * @author LiangGengguang
 * @create 2020-02-26 15:18
 */
public class BroadcastProducer {

    public static void main(String[] args) throws MQClientException, RemotingException, InterruptedException, MQBrokerException {

        DefaultMQProducer producer = new DefaultMQProducer("broadcast_producer");

        producer.setNamesrvAddr("localhost:19876;localhost:29876");
        producer.start();

        for (int i = 0; i < 10; i++) {
            Message msg = new Message("TopicTest", "TagA", ("hello broadcast_message" + i).getBytes());

            SendResult send = producer.send(msg);
            System.out.printf("%s%n", send);
        }
        producer.shutdown();
    }
}
