package com.ycao.automobile.controller;

import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.UserDomain;
import com.ycao.automobile.utils.Commons;
import com.ycao.automobile.utils.MapCache;
import com.ycao.automobile.utils.MyUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * @Type BaseController.java
 * @Desc this is the base controller all the controller must extend from
 * @author yuan.cao@utbm.fr
 * @date 25/05/2022 22:23
 * @version 1.0
 */
public abstract class BaseController {

    protected static final Logger LOGGER = LogManager.getLogger(BaseController.class);

    /**
     * used for stock cache, for example, how many times the wrong login attempt before blocking the ip
     */
    protected MapCache cache = MapCache.single();

    protected Integer getCurrentUserId(HttpServletRequest request){
        assert request!=null;
        UserDomain userDomain = MyUtils.getLoginUser(request);
        return userDomain==null?-1:userDomain.getId();
    }


    protected String getTotalPrice(List<ProductDomain> productDomains){
        Double res=0.0;
        for (ProductDomain product:productDomains){
            String singlePrice = Commons.getTotalPrice(String.valueOf(product.getPrice()),String.valueOf(product.getVdef2()));
            Double sp = Double.parseDouble(singlePrice.substring(0,singlePrice.length()-1));
            res+=sp;
        }
        return res+" €";
    }
}
