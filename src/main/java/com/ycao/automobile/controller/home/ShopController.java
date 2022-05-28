package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.SystemDomain;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.service.IShopService;
import com.ycao.automobile.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
public class ShopController extends BaseController {

    @Autowired
    IShopService iShopService;

    @Autowired
    IHomeService iHomeService;

    @GetMapping(value = {"/shop"})
    public String enterShop(HttpServletRequest request){
        LOGGER.info("Enter shop method");

        List<SystemDomain> allSystems  = iShopService.getAllSystem();
        request.setAttribute("allSystems", allSystems);

        request.setAttribute("numProducts", iShopService.getAllProductsNum());

        List<ProductDomain> first28Products  = iHomeService.getLatestProductCommentsPriority(28);
        request.setAttribute("first28Products", first28Products);

        LOGGER.info("Exit shop method");
        return "home/shop";
    }

    @GetMapping(value = {"/shop/product/{pdid}"})
    @ResponseBody
    public APIResponse getProductWithSubPieceId(HttpServletRequest request,
                                                @PathVariable Integer pdid){
        LOGGER.info("Enter get products method");
        LOGGER.info("Get products of sub-piece[+"+pdid+"]");

        assert pdid!=null;
        List<ProductDomain> productDomains = null;
        try{
            productDomains  = iShopService.getAllProductsWithPdid(pdid);

        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }

        LOGGER.info("Exit get products method");
        return APIResponse.success(productDomains);
    }

    @GetMapping(value = {"/shop/productLists/{pageNum}"})
    @ResponseBody
    public APIResponse lazyProducts(HttpServletRequest request,
                                                @PathVariable Integer pageNum
                                    ){
        LOGGER.info("Enter get products method");
        LOGGER.info("Get products of page ["+pageNum+"]");

        assert pageNum!=null;
        Integer start = (pageNum-1)*28;

        List<ProductDomain> productDomains = null;
        try{
            productDomains  = iHomeService.getLatestProductCommentsPriority(start,28);

        }catch (Exception e){
            return APIResponse.fail(e.getMessage());
        }

        LOGGER.info("Exit get products method");
        return APIResponse.success(productDomains);
    }
}
