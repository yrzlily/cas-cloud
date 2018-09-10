package com.yrz.user.controller;

import com.yrz.user.orm.User;
import com.yrz.user.repository.UserRepository;
import com.yrz.user.result.Result;
import com.yrz.user.result.ResultUtils;
import com.yrz.user.service.ClientService;
import com.yrz.user.utils.CookieUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.util.Enumeration;
import java.util.Objects;
import java.util.Optional;

/**
 * @author yrz
 */
@RestController
@RequestMapping(value = {"/user"})
public class UserController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private UserRepository userRepository;

    @RequestMapping(value = "/login" , method = RequestMethod.POST)
    public void login(@RequestParam("username")String username , @RequestParam("password")String password , @RequestParam("service")String services , HttpServletRequest request ,HttpServletResponse response) throws IOException {

        //认证中心门票入口
        String url = "http://127.0.0.1:8080/cas/v1/tickets";
        //请求方式
        HttpMethod method = HttpMethod.POST;

        //请求参数
        MultiValueMap<String, String> params= new LinkedMultiValueMap<String, String>();
        params.add("username" , username);
        params.add("password" , password);
        params.add("service" , services);

        //获取TGT
        String tgt = clientService.client(url , method , params);

        //获取st
        MultiValueMap<String, String> service= new LinkedMultiValueMap<String, String>();
        service.add("service" , services);
        String st = clientService.client(url+"/"+tgt , method , service);


        response.sendRedirect(services+"?ticket="+st);
    }

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/admin")
    public Principal admin(HttpServletRequest request){
        return request.getUserPrincipal();
    }

    @RequestMapping("/details")
    public User details(@RequestParam("username")String username){
        return userRepository.findByUsername(username);
    }

    /**
     * 添加用户
     * @param user 用户属性
     * @param bindingResult 错误信息
     * @return 成功返回
     */
    @RequestMapping(value = "/add" , method = RequestMethod.POST)
    public Result add(@Valid User user , BindingResult bindingResult){

        if(bindingResult.hasErrors()){
            return ResultUtils.error(0, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage() , user);
        }

        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());
        userRepository.save(user);

        return ResultUtils.success(user);
    }

    @RequestMapping(value = "/edit" , method = RequestMethod.POST)
    public Result edit(@Valid User user , BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResultUtils.error(0, Objects.requireNonNull(bindingResult.getFieldError()).getDefaultMessage() , user);
        }

        user.setId(user.getId());
        user.setUsername(user.getUsername());
        user.setPassword(user.getPassword());

        userRepository.save(user);

        return ResultUtils.success(user);
    }

    @RequestMapping("/logout")
    public Optional<String> logout(HttpServletRequest request){

        return Optional.ofNullable(CookieUtils.getCookieValue("JSESSIONID", request));
    }

    @RequestMapping("/cookie")
    public String session(HttpServletRequest request){
        //获取session
        HttpSession   session   =   request.getSession();
        // 获取session中所有的键值
        Enumeration<String> attrs = session.getAttributeNames();
        // 遍历attrs中的
        while(attrs.hasMoreElements()){
            // 获取session键值
            String name = attrs.nextElement().toString();
            // 根据键值取session中的值
            Object vakue = session.getAttribute(name);
            // 打印结果
            System.out.println("------" + name + ":" + vakue +"--------\n");
        }

        return CookieUtils.getCookieValue("TGC" , request);
    }


}
