package com.order_porint.controller;

import com.order_porint.activemq.Producer;
import com.order_porint.model.HotelOrder;
import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.jms.Destination;

/**
 * Created by zsx on 2018-08-31.
 */
@RequestMapping("auth/hotelOrder")
@RestController
public class HotelOrderCtrl {

    @Autowired
    private Producer producer;
    private static Destination destination = new ActiveMQQueue("order.queue");

    @RequestMapping(value = "/addOrder",method = RequestMethod.GET)
    public String createOrder(@RequestParam String username) {
        HotelOrder hotelOrder = new HotelOrder();
        hotelOrder.setUsername(username);
        hotelOrder.setHotelId("C100C23FA");
        System.out.println("----create order:" + hotelOrder.toString());
        producer.sendMessage(destination,hotelOrder);
        return "success";
    }
}
