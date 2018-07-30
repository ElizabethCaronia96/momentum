package com.momentum.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import java.util.Arrays;

@Service
public class MomentumMsgReceiver implements SessionAwareMessageListener<TextMessage> {

    public static void main(String[] args) {

        ClassPathXmlApplicationContext appContext = new ClassPathXmlApplicationContext("beans.xml");

        while (true) {

            // keep receiver open until error occurs or program exits

            try {
                Thread.sleep(0);
            } catch (InterruptedException e) {
                e.printStackTrace();
                appContext.close();
            }
        }
    }

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        // This is the received message
        System.out.println("Received a msg: " + message.getText());
    }
}