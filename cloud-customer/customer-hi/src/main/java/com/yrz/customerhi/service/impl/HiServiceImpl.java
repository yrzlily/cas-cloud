package com.yrz.customerhi.service.impl;

import com.yrz.customerhi.service.HiService;
import org.springframework.stereotype.Component;

/**
 * 断路器
 * @author yrz
 */
@Component
public class HiServiceImpl implements HiService {

    @Override
    public String hi(String name) {
        return "sorry!! "+ name;
    }
}
