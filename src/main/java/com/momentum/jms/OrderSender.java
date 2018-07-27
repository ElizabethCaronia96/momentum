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

        ApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");
        this.jmsMessageSender = (MomentumMsgSender) appContext.getBean("momentumMsgSender");
        this.queue = new ActiveMQQueue("OrderBroker");

    }


    public void send(Order order) {

        String orderXML = "<trade>\n" +
                "<buy>true</buy>\n" +
                "<id>0</id>\n" +
                "<price>123.0</price>\n" +
                "<size>1234</size>\n" +
                "<stock>GOOGL</stock>\n" +
                "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                "</trade>";


        jmsMessageSender.send(queue, orderXML);

    }
}
