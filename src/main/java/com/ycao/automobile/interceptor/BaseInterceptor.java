package com.ycao.automobile.interceptor;

import com.ycao.automobile.constant.CommonConstant;
import com.ycao.automobile.constant.ErrorConstant;
import com.ycao.automobile.model.UserDomain;
import com.ycao.automobile.service.IUserService;
import com.ycao.automobile.utils.Commons;
import com.ycao.automobile.utils.IPKit;
import com.ycao.automobile.utils.MyUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";

    @Autowired
    private Commons commons;

    @Autowired
    private IUserService iUserService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        LOGGER.info("UserAgent: {}",request.getHeader(USER_AGENT));
        LOGGER.info("User access address: {}, coming address: {}",url, IPKit.getIpAddrByRequest(request));

        //Request interception processing
        UserDomain user = MyUtils.getLoginUser(request);
        if (null == user) {
            Integer uid = MyUtils.getCookieUid(request);
            if (null != uid) {
                //There are still security risks here, cookies can be forged
                user = iUserService.getUserInfoById(uid);
                request.getSession().setAttribute(CommonConstant.Web.LOGIN_SESSION_KEY, user);
            }
        }
        /**
         * pass if : the static under admin,
         *           not start with admin
         *           start with admin but login
         *           admin login page
         */
        if ((url.startsWith("/admin")||url.startsWith("/cart")||url.startsWith("/checkout")) && null == user
                && !url.startsWith("/admin/login")
                && !url.startsWith("/admin/css")
                && !url.startsWith("/admin/images")
                && !url.startsWith("/admin/js")
                && !url.startsWith("/admin/plugins")) {
            response.sendRedirect("/login");
//            response.sendError(ErrorConstant.HttpStatus.RESOURCE_UNAUTHORIZED);
            return false;
        }
        return true;
    }

    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {
        httpServletRequest.setAttribute("commons", commons);//Some utility classes and public methods
    }
}
