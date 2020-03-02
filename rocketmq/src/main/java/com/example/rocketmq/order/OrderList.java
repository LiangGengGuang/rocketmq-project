package com.example.rocketmq.order;

import java.util.ArrayList;
import java.util.List;

/**
 * @author LiangGengguang
 * @create 2020-03-02 22:39
 */
public class OrderList {

    private int orderId;

    private String msg;


    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    @Override
    public String toString() {
        return "orderId：" + getOrderId() + "msg：" + getMsg();
    }

    public static List<OrderList> buildOrderList() {
        List<OrderList> list = new ArrayList<>();

        OrderList orderList = new OrderList();
        orderList.setOrderId(1039);
        orderList.setMsg("创建");
        list.add(orderList);

        orderList = new OrderList();
        orderList.setOrderId(1065);
        orderList.setMsg("创建");
        list.add(orderList);

        orderList = new OrderList();
        orderList.setOrderId(1065);
        orderList.setMsg("付款");
        list.add(orderList);

        orderList = new OrderList();
        orderList.setOrderId(1039);
        orderList.setMsg("付款");
        list.add(orderList);

        orderList = new OrderList();
        orderList.setOrderId(7235);
        orderList.setMsg("创建");
        list.add(orderList);

        orderList = new OrderList();
        orderList.setOrderId(1039);
        orderList.setMsg("完成");
        list.add(orderList);

        return list;
    }
}
