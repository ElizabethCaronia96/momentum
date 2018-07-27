package com.citi.trading;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Service;

@Service
public class MyMessageSender {
    @Autowired
    private JmsTemplate jmsTemplate;
}
