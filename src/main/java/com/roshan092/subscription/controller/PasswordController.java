package com.roshan092.subscription.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;

@Controller
public class PasswordController {
    private static final Logger LOGGER = LoggerFactory.getLogger(PasswordController.class);

    @RequestMapping(value = "/password/change", method = RequestMethod.GET)
    public String change(HttpServletRequest httpServletRequest) {
        return "changePassword";
    }
}
