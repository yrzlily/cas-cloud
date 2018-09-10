package com.yrz.user.utils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
 * @author yrz
 */

public class CookieUtils {

    /**
     * 获取cookie值
     * @param key
     * @param request
     * @return
     */
    public static String getCookieValue(String key , HttpServletRequest request){
        String value = null;
        Cookie[] cookie = request.getCookies();
        for (Cookie cookies : cookie) {
            if(key.equals(cookies.getName())){
                value = cookies.getValue();
            }
        }
        return value;
    }

    /**
     * 删除cookie
     * @param key 键值
     */
    public static void deleteCookie(String key){
        Cookie cookie = new Cookie(key , "");
        cookie.setMaxAge(0);
    }

    public static Cookie[] allCookie(HttpServletRequest request){
        return request.getCookies();
    }

    /**
     * 储存session
     * @param session
     * @param key
     * @param value
     */
    public static void addSession(HttpSession session , String key , String value){
        session.setAttribute(key , value);
    }
}
