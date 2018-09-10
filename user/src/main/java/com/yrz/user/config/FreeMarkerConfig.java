package com.yrz.user.config;

import cn.org.rapid_framework.freemarker.directive.BlockDirective;
import cn.org.rapid_framework.freemarker.directive.ExtendsDirective;
import cn.org.rapid_framework.freemarker.directive.OverrideDirective;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

/**
 * FreeMarker配置
 * @author yrz
 */
@Component
public class FreeMarkerConfig {

    @Autowired
    private freemarker.template.Configuration configuration;

    /**
     *
     * @throws Exception
     */
    @PostConstruct
    public void setSharedVariable() throws Exception {
        //模板继承
        configuration.setSharedVariable("block" , new BlockDirective());
        configuration.setSharedVariable("override" , new OverrideDirective());
        configuration.setSharedVariable("extends" , new ExtendsDirective());
    }

}
