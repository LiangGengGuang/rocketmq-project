package com.example.rocketmq.transaction;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.*;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

import java.util.concurrent.TimeUnit;

/**
 * @author LiangGengguang
 * @create 2020-03-03 00:10
 */
public class TransactionProducer {

    public static void main(String[] args) throws Exception {
        TransactionMQProducer producer = new TransactionMQProducer("transaction_producer");

        producer.setNamesrvAddr("localhost:19876;localhost:29876");

        //添加事务的监听器
        producer.setTransactionListener(new TransactionListener() {
            /**
             * 在该方法中执行的本地事务
             * @param msg
             * @param arg
             * @return
             */
            @Override
            public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
                if (StringUtils.equals("Tag1", msg.getTags())) {
                    return LocalTransactionState.COMMIT_MESSAGE;
                } else if (StringUtils.equals("Tag2", msg.getTags())) {
                    return LocalTransactionState.ROLLBACK_MESSAGE;
                } else if (StringUtils.equals("Tag3", msg.getTags())) {
                    return LocalTransactionState.UNKNOW;
                }
                return LocalTransactionState.UNKNOW;
            }

            /**
             * MQ进行消息事务的回查
             * @param msg
             * @return
             */
            @Override
            public LocalTransactionState checkLocalTransaction(MessageExt msg) {
                System.out.println(msg.getTags());
                return LocalTransactionState.COMMIT_MESSAGE;
            }
        });
        producer.start();

        String[] tags = {"Tag1", "Tag2", "Tag3"};
        for (int i = 0; i < tags.length; i++) {

            Message msg = new Message("TopicTest", tags[i], "key" + i, ("transaction message" + i).getBytes());
            SendResult send = producer.sendMessageInTransaction(msg, null);

            send.getSendStatus();   //表示消息发送结果的状态
            send.getMsgId();        //注意这里的命名虽然是msgId，但实际上其是Unique Key
            send.getOffsetMsgId();  //Broker返回的Message ID 。未进行特殊说明的情况下，Message ID总是表示offsetMsgId。
            send.getMessageQueue(); //消息发送到了哪个的队列
            send.getQueueOffset();  //消息在队列中的偏移量，每次发送到一个队列时，offset+1

            System.out.println(send);
            TimeUnit.SECONDS.sleep(1);
        }

//        producer.shutdown();
    }
}
