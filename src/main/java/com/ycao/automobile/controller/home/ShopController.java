package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.SystemDomain;
import com.ycao.automobile.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopController extends BaseController {

    @Autowired
    IShopService iShopService;

    @GetMapping(value = {"/shop"})
    public String enterShop(HttpServletRequest request){
        LOGGER.info("Enter shop method");

        List<SystemDomain> allSystems  = iShopService.getAllSystem();
        request.setAttribute("allSystems", allSystems);

        request.setAttribute("numProducts", iShopService.getAllProductsNum());

        LOGGER.info("Exit shop method");
        return "home/shop";
    }
}
