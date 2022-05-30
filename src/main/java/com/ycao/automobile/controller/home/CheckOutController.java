package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.ICartService;
import com.ycao.automobile.service.ICommandService;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.service.IProductService;
import com.ycao.automobile.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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
    @Autowired
    ICommandService iCommandService;

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

    @PostMapping(value = "command")
    public String fileUploadToCloud(HttpServletRequest request,
                                         HttpServletResponse response){
        try {

            request.setCharacterEncoding("utf-8");

            MultipartHttpServletRequest params=((MultipartHttpServletRequest) request);
            String billing_country=params.getParameter("billing_country");
            String billing_first_name=params.getParameter("billing_first_name");
            String billing_last_name=params.getParameter("billing_last_name");
            String billing_address_1=params.getParameter("billing_address_1");
            String billing_address_2=params.getParameter("billing_address_2");
            String billing_company=params.getParameter("billing_company");
            String billing_city=params.getParameter("billing_city");
            String billing_state=params.getParameter("billing_state");
            String billing_postcode=params.getParameter("billing_postcode");
            String billing_email=params.getParameter("billing_email");
            String billing_phone=params.getParameter("billing_phone");

            String ship_to_different_address=params.getParameter("ship_to_different_address");
            String shipping_country=params.getParameter("shipping_country");
            String shipping_first_name=params.getParameter("shipping_first_name");
            String shipping_last_name=params.getParameter("shipping_last_name");
            String shipping_company=params.getParameter("shipping_company");
            String shipping_address_1=params.getParameter("shipping_address_1");
            String shipping_address_2=params.getParameter("shipping_address_2");
            String shipping_city=params.getParameter("shipping_city");
            String shipping_state=params.getParameter("shipping_state");
            String shipping_postcode=params.getParameter("shipping_postcode");
            String order_comments=params.getParameter("order_comments");

            iCommandService.saveCommand(getCurrentUserId(request),billing_country,billing_first_name,billing_last_name,billing_company,billing_address_1,billing_address_2,billing_city,billing_state,billing_postcode,billing_email,billing_phone,ship_to_different_address,shipping_country,shipping_first_name,shipping_last_name,shipping_company,shipping_address_1,shipping_address_2,shipping_city,shipping_state,shipping_postcode,order_comments);

            response.setHeader("Content-Type", "text/html");

            return "redirect:/admin/myCommand";
        }  catch (Exception e) {
            return "redirect:/500";
        }
    }
}
