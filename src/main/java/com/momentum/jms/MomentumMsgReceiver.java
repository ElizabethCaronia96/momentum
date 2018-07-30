package com.momentum.jms;

import javax.jms.JMSException;
import javax.jms.Session;
import javax.jms.TextMessage;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.apache.activemq.broker.Broker;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.jms.listener.SessionAwareMessageListener;
import org.springframework.stereotype.Service;

import java.io.StringReader;
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

        BrokerReply reply = convertXMLtoBrokerReply(message.getText());



//        the lines below pretty print an object to see if the XML was actually converted

//        System.out.println("REPLY OBJECT:");
//        ObjectMapper om = new ObjectMapper();
//        om.enable(SerializationFeature.INDENT_OUTPUT); //pretty print
//        String s = null;
//        try {
//            s = om.writeValueAsString(reply);
//            System.out.println(s);
//
//        } catch (JsonProcessingException e) {
//            e.printStackTrace();
//        }

    }

    public BrokerReply convertXMLtoBrokerReply(String XML) {

        BrokerReply reply = null;

        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(BrokerReply.class);
            Unmarshaller unmarshaller = jaxbContext.createUnmarshaller();
            StringReader reader = new StringReader(XML);
            reply = (BrokerReply) unmarshaller.unmarshal(reader);
        } catch (JAXBException e) {
            e.printStackTrace();
        }

        return reply;
    }
}