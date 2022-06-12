package com.ycao.automobile.controller.home;

import com.ycao.automobile.constant.CommonConstant;
import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.exception.BusinessException;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.UserDomain;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.service.IUserService;
import com.ycao.automobile.utils.APIResponse;
import com.ycao.automobile.utils.IPKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@Controller
public class HomeController extends BaseController {

    @Autowired
    IHomeService iHomeService;

    @GetMapping(value = {"","/index"})
    public String enterIndex(HttpServletRequest request){
        LOGGER.info("Enter index method");
        //slider data
        List<ProductDomain> sliderProducts = iHomeService.getHighNoteProductHasComments(4);
        request.setAttribute("sliderProducts", sliderProducts);

        List<ProductDomain> latestProducts = iHomeService.getLatestProductHasComments(6);
        request.setAttribute("latestProducts", latestProducts);

        List<ProductDomain> bestSoldProducts = iHomeService.getBestSeller(3);
        request.setAttribute("bestSoldProducts", bestSoldProducts);

        LOGGER.info("Exit index method");
        return "home/index";
    }

    @GetMapping(value = "/login")
    public String toLogin(HttpServletRequest request){

        return "admin/login";
    }

    @Autowired
    IUserService iUserService;

    @PostMapping(value = "/admin/login")
    @ResponseBody
    public APIResponse toLogin(
            HttpServletRequest request,
            HttpServletResponse response,
            @RequestParam(name = "username", required = true)
                    String username,
            @RequestParam(name = "password", required = true)
                    String password,
            @RequestParam(name = "remeber_me", required = false)
                    String remeber_me
    ){

        String ip= IPKit.getIpAddrByRequest(request); // Get ip and filter cached bugs when logging in
        Integer error_count = cache.hget("login_error_count",ip);
        try {
            UserDomain userInfo = iUserService.login(username, password);
            request.getSession().setAttribute(CommonConstant.Web.LOGIN_SESSION_KEY, userInfo);
            //edited by ycao on 13/8/2021
            request.getSession().setMaxInactiveInterval(60*30);//session 30min expired
//            if (StringUtils.isNotBlank(remeber_me)) {
//                MyUtils.setCookie(response, userInfo.getId());
//            }
        } catch (Exception e) {
            LOGGER.error(e.getMessage());
            error_count = null == error_count ? 1 : error_count + 1;
//            if (error_count > 10000) {
            if (error_count > 3) {// debug proposal, should be restored
                return APIResponse.fail("You entered the wrong password more than 3 times, please try again after 10 minutes");
            }
            cache.hset("login_error_count", ip,error_count, 10 * 60); // add ip filter
            String msg = "login fails";
            if (e instanceof BusinessException) {
                msg = ((BusinessException) e).getErrorCode();
            } else {
                LOGGER.error(msg, e);
            }
            return APIResponse.fail(msg);
        }

        return APIResponse.success();

    }

    /**
     * log out
     *
     * @param session
     * @param response
     */
    @GetMapping("/logout")
    public void logout(HttpSession session, HttpServletResponse response) {
        session.removeAttribute(CommonConstant.Web.LOGIN_SESSION_KEY);
        Cookie cookie = new Cookie(CommonConstant.Web.USER_IN_COOKIE, "");
        cookie.setValue(null);
        cookie.setMaxAge(0);// Destroy cookies immediately
        cookie.setPath("/");
        response.addCookie(cookie);
        try {
            response.sendRedirect("/index");
        } catch (IOException e) {
            e.printStackTrace();
            LOGGER.error("log out fails", e);
        }
    }

    @GetMapping(value = "/500")
    public String toServerError(HttpServletRequest request){

        return "common/500";
    }
}
