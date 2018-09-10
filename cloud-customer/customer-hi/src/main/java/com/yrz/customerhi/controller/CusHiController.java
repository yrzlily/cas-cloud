package com.yrz.customerhi.controller;

import com.yrz.customerhi.service.HiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author yrz
 */
@RestController
public class CusHiController {

    private final HiService hiService;

    @Autowired
    public CusHiController(HiService hiService) {
        this.hiService = hiService;
    }

    @RequestMapping("/hi")
    public String hi(@RequestParam("name") String name){
        return hiService.hi(name);
    }

}
