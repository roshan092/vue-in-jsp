package com.newscorp.subscription.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class SubscriptionController {

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String subscribe() {
        return "order";
    }
}
