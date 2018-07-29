package com.momentum.jms;


import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
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

    public OrderSender(ApplicationContext appContext) {

        BeanDefinitionRegistry appContextBeanFactory = (BeanDefinitionRegistry) appContext.getAutowireCapableBeanFactory();
        appContextBeanFactory.removeBeanDefinition("messageListenerContainer");

        this.jmsMessageSender = (MomentumMsgSender) appContext.getBean("momentumMsgSender");
        this.queue = new ActiveMQQueue("OrderBroker");

    }

    public void send(Order order) {

        String orderXML = String.format("<trade><buy>%s</buy><id>%s</id><price>%s</price><size>%s</size><stock>%s</stock><whenAsDate>%s</whenAsDate></trade>",
                order.getBuyXML(), order.getIdXML(), order.getPriceXML(),
                order.getSizeXML(), order.getStockXML(), order.getWhenAsDateXML());

        System.out.println("Sending: " + orderXML);

        jmsMessageSender.send(queue, orderXML, order.getIdXML());

    }
}
