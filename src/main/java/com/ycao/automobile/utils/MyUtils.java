package com.ycao.automobile.utils;

import com.ycao.automobile.constant.CommonConstant;
import com.ycao.automobile.model.UserDomain;
import com.ycao.automobile.utils.security.SecutiryUtils;
import org.apache.commons.lang3.StringUtils;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Type MyUtils.java
 * @Desc methods frequent used
 * @author yuan.cao@utbm.fr
 * @date 26/05/2022 15:39
 * @version 1.0
 */
public class MyUtils {

    public static Integer strToInt(String str){
        assert str!=null;
        Double db = Double.valueOf(str);
        Long dbR = Math.round(db);
        return new Long(dbR).intValue();
    }


    /**
     * Set remember password cookies
     *
     * @param response
     * @param uid
     */
    public static void setCookie(HttpServletResponse response, Integer uid) {
        try {
            String val = SecutiryUtils.enAes(uid.toString(), CommonConstant.Web.AES_SALT);
            boolean isSSL = false;
            Cookie cookie = new Cookie(CommonConstant.Web.USER_IN_COOKIE, val);
            cookie.setPath("/");
            cookie.setMaxAge(60 * 30);
            cookie.setSecure(isSSL);
            response.addCookie(cookie);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     * Get the specified cookie from cookies
     *
     * @param name
     * @param request
     * @return cookie
     */
    private static Cookie cookieRaw(String name, HttpServletRequest request) {
        javax.servlet.http.Cookie[] servletCookies = request.getCookies();
        if (servletCookies == null) {
            return null;
        }
        for (javax.servlet.http.Cookie c : servletCookies) {
            if (c.getName().equals(name)) {
                return c;
            }
        }
        return null;
    }

    /**
     * Get user id in cookie
     *
     * @param request
     * @return
     */
    public static Integer getCookieUid(HttpServletRequest request) {
        if (null != request) {
            Cookie cookie = cookieRaw(CommonConstant.Web.USER_IN_COOKIE, request);
            if (cookie != null && cookie.getValue() != null) {
                try {
                    String uid = SecutiryUtils.deAes(cookie.getValue(), CommonConstant.Web.AES_SALT);
                    return StringUtils.isNotBlank(uid) && isNumber(uid) ? Integer.valueOf(uid) : null;
                } catch (Exception e) {
                }
            }
        }
        return null;
    }

    /**
     * Check if a string is a number and has the correct value
     *
     * @param str
     * @return
     */
    public static boolean isNumber(String str) {
        // Pattern pattern=Pattern.compile("[0-9]*");
        // return pattern.matcher(str).matches();
        if (null != str && 0 != str.trim().length() && str.matches("\\d*")) {
            return true;
        }

        return false;
    }


    /**
     * Returns the currently logged in user
     *
     * @return
     */
    public static UserDomain getLoginUser(HttpServletRequest request) {
        HttpSession session = request.getSession();
        if (null == session) {
            return null;
        }
        return (UserDomain) session.getAttribute(CommonConstant.Web.LOGIN_SESSION_KEY);
    }

    /**
     * @DESC Generate random ID: current hour, minute, second + two random numbers
     * @param
     * @return 8 numbers
     * @data 16/05/2022 11:44
     * @author yuan.cao@utbm.fr
     **/
    public static Integer getRandomID() {

        SimpleDateFormat simpleDateFormat;

        simpleDateFormat = new SimpleDateFormat("HHmmss");

        Date date = new Date();

        String str = simpleDateFormat.format(date);

        int rannum = (int)(1+Math.random() * 99);// 获取随机数1-99

        return Integer.valueOf(str + rannum);// 当前时间
    }
}
