package com.order_porint.activemq;

import com.alibaba.fastjson.JSONObject;
import com.order_porint.model.HotelOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.messaging.simp.SimpMessageSendingOperations;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;

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

            //String loginUserName = SecurityContextHolder.getContext().getAuthentication().getName();
            //param user ==> 认证username
            messagingTemplate.convertAndSendToUser("first@163.com","/secured/history/ordersQueue",orderObject);
        }
    }
}
