package com.yrz.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author yrz
 */
@RequestMapping("/login")
@Controller
public class PageController {

    @RequestMapping(value = "/index" , method = RequestMethod.GET)
    public String login(){
        return "login";
    }

}
