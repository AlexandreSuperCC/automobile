package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.ICartService;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/checkout")
public class CheckOutController extends BaseController {

    @Autowired
    IHomeService iHomeService;
    @Autowired
    IProductService iProductService;
    @Autowired
    ICartService iCartService;

    @GetMapping(value = {""})
    public String enterCheckout(HttpServletRequest request){
        LOGGER.info("Enter checkout method");

        List<ProductDomain> highNoteProducts = iHomeService.getHighNoteProductHasComments(4);
        List<ProductDomain> latestProducts = iHomeService.getLatestProductHasComments(5);


        //set cart
        List<ProductDomain> productsInCartList = iCartService.getCartOfUser(getCurrentUserId(request));
        request.setAttribute("productsInCartList", productsInCartList);

        //total price
        String totalPrice = getTotalPrice(productsInCartList);
        request.setAttribute("totalPrice", totalPrice);
        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("highNoteProducts", highNoteProducts);



        LOGGER.info("Exit checkout method");
        return "home/checkout";
    }
}
