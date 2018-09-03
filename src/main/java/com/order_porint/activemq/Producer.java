package com.order_porint.activemq;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Destination;

/**
 * Created by zsx on 2018-08-31.
 */
@Service
public class Producer {
    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    public void sendMessage(Destination destination, Object message) {
        System.out.println("---- producer message [message]: " + message.toString());
        jmsMessagingTemplate.convertAndSend(destination,message.toString());
    }
}
