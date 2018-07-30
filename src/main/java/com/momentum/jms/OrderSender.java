package com.momentum.jms;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import javax.jms.Queue;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class OrderSender {

    private MomentumMsgSender jmsMessageSender;
    private Queue queue;

    public OrderSender() {

    }


    public void send(Order order) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        // get bean from context
        MomentumMsgSender jmsMessageSender = (MomentumMsgSender) ctx.getBean("momentumMsgSender");


        // send to a code specified destination
        Queue queue = new ActiveMQQueue("OrderBroker");
        jmsMessageSender.send(queue, order.parseOrder());


        try {
            // give time for the receiver to get the messages
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // close spring application context
        ((ClassPathXmlApplicationContext) ctx).close();
    }
}
