package com.momentum.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;

import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

@Service
public class MomentumMsgReceiver implements SessionAwareMessageListener<TextMessage> {

    @Override
    public void onMessage(TextMessage message, Session session) throws JMSException {
        // This is the received message
        System.out.println("Received: "+message.getText());
    }
}