package com.momentum.jms;

import javax.jms.Queue;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class TestMessaging {

    public static void main(String[] args) {
        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans.xml");

        // get bean from context
        MomentumMsgSender jmsMessageSender = (MomentumMsgSender)ctx.getBean("momentumMsgSender");


        // example xml to send
        String exampleXML = "<trade>\n" +
                "<buy>true</buy>\n" +
                "<id>0</id>\n" +
                "<price>123.0</price>\n" +
                "<size>1234</size>\n" +
                "<stock>GOOGL</stock>\n" +
                "<whenAsDate>2014-07-31T22:33:22.801-04:00</whenAsDate>\n" +
                "</trade>";

        // send to a code specified destination
        Queue queue = new ActiveMQQueue("OrderBroker");
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);
        jmsMessageSender.send(queue, exampleXML);

        try {
            // give time for the receiver to get the messages
            Thread.sleep(100000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        // close spring application context
        ((ClassPathXmlApplicationContext)ctx).close();
    }

}