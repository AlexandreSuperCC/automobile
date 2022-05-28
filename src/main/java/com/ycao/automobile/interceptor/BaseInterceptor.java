package com.ycao.automobile.interceptor;

import com.ycao.automobile.utils.IPKit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class BaseInterceptor implements HandlerInterceptor {
    private static final Logger LOGGER = LoggerFactory.getLogger(BaseInterceptor.class);
    private static final String USER_AGENT = "user-agent";


    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String url = request.getRequestURI();
        LOGGER.info("UserAgent: {}",request.getHeader(USER_AGENT));
        LOGGER.info("User access address: {}, coming address: {}",url, IPKit.getIpAddrByRequest(request));

        // 如果不是映射到方法直接通过,可以访问资源.
        // if the action is uploading file, it will not come through the front-end interceptors!!!, so let it pass
        if(url.startsWith("/login")
                ||url.contains("/uploadFile")
                ||(!(handler instanceof HandlerMethod)
                //不拦截swagger
                || url.contains("/swagger") || url.contains("/webjars/")
        )){
            return true;
        }

        return true;
    }
}
