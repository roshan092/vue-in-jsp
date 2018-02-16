package com.newscorp.subscription.controller;

import com.newscorp.subscription.service.ILogUtilService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class SubscriptionController {
    private static final Logger LOGGER = LoggerFactory.getLogger(SubscriptionController.class);

    @Autowired
    private ILogUtilService logUtilService;
    @Value("${log.truncate.length}")
    private int logTruncateLength;

    @RequestMapping(value = "/subscribe", method = RequestMethod.GET)
    public String subscribe(HttpServletRequest httpServletRequest) {
        String orderDetails = getOrderDetails(httpServletRequest);
        String truncatedOrderDetails = logUtilService.truncate(orderDetails, logTruncateLength);
        LOGGER.info(truncatedOrderDetails);
        return "order";
    }

    private String getOrderDetails(HttpServletRequest httpServletRequest) {
        //Do things if required to get the correct order details string
        //Assuming that the order details are sent as a string in the request params
        Object orderDetails = httpServletRequest.getParameter("orderDetails");
        return orderDetails == null ? "" : (String) orderDetails;
    }
}
