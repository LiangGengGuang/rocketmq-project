package com.example.rocketmq.basic;

import org.apache.rocketmq.client.producer.DefaultMQProducer;
import org.apache.rocketmq.client.producer.SendCallback;
import org.apache.rocketmq.client.producer.SendResult;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.remoting.common.RemotingHelper;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author LiangGengguang
 * @create 2020-02-21 11:47
 */

public class Producer {
    public static void main(String[] args) throws Exception {
//      可靠的同步传输广泛应用于重要通知消息，短信通知，短信营销系统等
        reliableSynchronous();
//      异步传输通常用于响应时间敏感的业务场景。
//        reliableAsynchronous();
//        单向传输用于需要中等可靠性的情况，如日志收集。
//        oneWay();

    }

    static void reliableSynchronous() throws Exception {

//        初始化
        DefaultMQProducer producer = new DefaultMQProducer("synchronous"); //不能有空格
//        配置namesrv的地址
//        producer.setNamesrvAddr("localhost:9876");
        producer.setNamesrvAddr("localhost:19876;localhost:29876");
        producer.start();

        for (int i = 0; i < 10; i++) {
//            创建一个消息实例，指定topic，tags和message body。
            Message msg = new Message("TopicTest", "TagA", "synchronousID233", 0, ("Hello reliable synchronous " + i).getBytes(), true);
//            呼叫发送消息以将消息传递给其中一个代理。
            SendResult sendResult = producer.send(msg);
            System.out.printf("%s%n", sendResult);

            TimeUnit.SECONDS.sleep(1);
        }
//        关闭producer生产
        producer.shutdown();
    }

    static void reliableAsynchronous() throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("reliable_asynchronous");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();
        producer.setRetryTimesWhenSendAsyncFailed(0);

        int messageCount = 100;//发送消息数量
        final CountDownLatch countDownLatch = new CountDownLatch(messageCount);//定义条件

        for (int i = 0; i < messageCount; i++) {
            final int index = i;
            Message msg = new Message("TopicTest", "TagB", "OrderID188", "Hello reliable asynchronous".getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.send(msg, new SendCallback() {
                @Override
                public void onSuccess(SendResult sendResult) {
                    System.out.printf("%-10d OK %s %n", index, sendResult.getMsgId());
                }

                @Override
                public void onException(Throwable e) {
                    System.out.printf("%-10d Exception %s %n", index, e);
                    e.printStackTrace();
                }
            });
        }
        countDownLatch.await(5, TimeUnit.SECONDS);//5秒后关闭生产（发送者）
        producer.shutdown();
    }

    static void oneWay() throws Exception {

        DefaultMQProducer producer = new DefaultMQProducer("one_way");
        producer.setNamesrvAddr("localhost:9876");
        producer.start();

        for (int i = 0; i < 100; i++) {
            Message msg = new Message("TopicTest", "tagsC", ("hello one-way" + i).getBytes(RemotingHelper.DEFAULT_CHARSET));
            producer.sendOneway(msg);
        }
        producer.shutdown();
    }
}
