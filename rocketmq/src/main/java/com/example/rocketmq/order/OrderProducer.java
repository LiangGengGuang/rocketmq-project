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


        List<OrderList> orderList = OrderList.buildOrderList();

        for (int i = 0; i < orderList.size(); i++) {

            String body = orderList.get(i) + "";

            Message msg = new Message("TopicTest", "order", "KEY" + i, body.getBytes());

            SendResult sendResult = producer.send(msg, new MessageQueueSelector() {
                /**
                 *
                 * @param msglist   队列集合
                 * @param msg       消息对象
                 * @param o         业务标识的参数
                 * @return
                 */
                @Override
                public MessageQueue select(List<MessageQueue> msglist, Message msg, Object o) {
                    Integer orderId = (Integer) o;
                    int index = orderId % msglist.size();
                    return msglist.get(index);
                }
            }, orderList.get(i).getOrderId());

            System.out.println("发送结果：" + sendResult);
        }
        producer.shutdown();
    }
}
