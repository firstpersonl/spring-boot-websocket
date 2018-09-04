package com.order_porint.activemq;

import com.order_porint.model.HotelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

/**
 * Created by zsx on 2018-08-31.
 */
@Component
public class Consumer {

    @Autowired
    private SimpMessagingTemplate messagingTemplate;

    @JmsListener(destination = "order.queue")
    public void receiveOrderQueue(Object orderObject) {
        if (orderObject!=null) {
            System.out.println("----consumer message [order]: " + orderObject.toString());

            //param user ==> 认证username
            messagingTemplate.convertAndSendToUser("user1","/secured/history/ordersQueue","message test");
        }
    }
}
