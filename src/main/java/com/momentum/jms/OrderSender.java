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

        String orderXML = String.format(
                "<trade>\n" +
                "<buy>%s</buy>\n" +
                "<id>%s</id>\n" +
                "<price>%s</price>\n" +
                "<size>%s</size>\n" +
                "<stock>%s</stock>\n" +
                "<whenAsDate>%s</whenAsDate>\n" +
                "</trade>",
                order.getBuyXML(), order.getIdXML(), order.getPriceXML(),
                order.getSizeXML(), order.getStockXML(), order.getWhenAsDateXML());

        jmsMessageSender.send(queue, orderXML);

    }
}
