package com.order_porint.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class IndexController {
    @RequestMapping(value = {"/",""})
    public String index() {
        return "/index";
    }

    @RequestMapping("/login")
    public String login() {
        return "/login";
    }

    @RequestMapping("/createOrder")
    public String createOrder() {
        return "/createOrder";
    }
}
