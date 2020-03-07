package com.example.rocketmq.Filter;

import org.apache.rocketmq.client.consumer.DefaultMQPushConsumer;
import org.apache.rocketmq.client.consumer.MessageSelector;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyContext;
import org.apache.rocketmq.client.consumer.listener.ConsumeConcurrentlyStatus;
import org.apache.rocketmq.client.consumer.listener.MessageListenerConcurrently;
import org.apache.rocketmq.client.exception.MQClientException;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.List;

/**
 * @author LiangGengguang
 * @create 2020-03-02 23:51
 */
public class FilterConsumer {

    public static void main(String[] args) throws MQClientException {

        DefaultMQPushConsumer consumer = new DefaultMQPushConsumer("filter_consumer");

        consumer.setNamesrvAddr("localhost:19876;localhost:29876");

        //指定所有Message中要获取的tags
//        consumer.subscribe("TopicTest", "TagA || TagC || TagD");

        //sql语法过滤
        consumer.subscribe("TopicTest", MessageSelector.bySql("a between 0 and 3"));

        consumer.registerMessageListener(new MessageListenerConcurrently() {
            @Override
            public ConsumeConcurrentlyStatus consumeMessage(List<MessageExt> list, ConsumeConcurrentlyContext consumeConcurrentlyContext) {

                for (MessageExt me : list) {

                    System.out.println("消息接收:" + new String(me.getBody()));
                }

                return ConsumeConcurrentlyStatus.CONSUME_SUCCESS;
            }
        });

        consumer.start();
        System.out.println("启动完成");
    }
}
