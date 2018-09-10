package com.yrz.customerhi.service;

import com.yrz.customerhi.service.impl.HiServiceImpl;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author yrz
 */
@FeignClient(value = "service-his" , fallback = HiServiceImpl.class)
public interface HiService {

    /**
     * 调用服务
     * @param name 用户名
     * @return string
     */
    @RequestMapping(value = "/hi" , method = RequestMethod.GET)
     String hi(@RequestParam("name") String name);

}
