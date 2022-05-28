package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.IHomeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletRequest;
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

    @GetMapping(value = {"/cart"})
    public String enterCart(HttpServletRequest request){
        LOGGER.info("Enter cart method");
        LOGGER.info("Exit cart method");
        return "home/cart";
    }

    @GetMapping(value = {"/checkout"})
    public String enterCheckout(HttpServletRequest request){
        LOGGER.info("Enter checkout method");
        LOGGER.info("Exit checkout method");
        return "home/checkout";
    }
}
