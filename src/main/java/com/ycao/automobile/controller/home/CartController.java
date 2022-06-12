package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.ICartService;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.service.IProductService;
import com.ycao.automobile.utils.APIResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping(value = "/cart")
public class CartController extends BaseController {

    @Autowired
    IHomeService iHomeService;
    @Autowired
    IProductService iProductService;
    @Autowired
    ICartService iCartService;

    @GetMapping(value = {""})
    public String enterCart(HttpServletRequest request){
        LOGGER.info("Enter cart method");

        List<ProductDomain> highNoteProducts = iHomeService.getHighNoteProductHasComments(4);
        List<ProductDomain> latestProducts = iHomeService.getLatestProductHasComments(5);


        //set cart
        List<ProductDomain> productsInCartList = iCartService.getCartOfUser(getCurrentUserId(request));
        request.setAttribute("productsInCartList", productsInCartList);

        //total price
        String totalPrice = getTotalPrice(productsInCartList);
        request.setAttribute("totalPrice", totalPrice);

        //you may be interested in part
        List<ProductDomain> relatedProducts;
        if(productsInCartList.size()>0){
            ProductDomain pd = productsInCartList.toArray(new ProductDomain[0])[0];
            String name = pd.getName().split(" ")[0];
            relatedProducts = iProductService.getRelatedProducts(name,pd.getId(),2);
        }else{
            relatedProducts = new ArrayList<>();
            relatedProducts.add(iProductService.getBestProduct());
        }

        request.setAttribute("relatedProducts", relatedProducts);
        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("highNoteProducts", highNoteProducts);

        LOGGER.info("Exit cart method");
        return "home/cart";
    }

    @PostMapping("/addToCart")
    public APIResponse addProductToCast(Integer pid,Integer uid){

        return null;
    }


    @PostMapping(value = "/admin/deleteProduct/{id}")
    @ResponseBody
    public APIResponse deleteCartProduct(HttpServletRequest request,
                                     @PathVariable Integer id){
        LOGGER.info("Delete product in cart method");

        try{
            iCartService.deleteProductInCart(id,getCurrentUserId(request));

            LOGGER.info("Exit delete product in cart method");
            return APIResponse.success("Delete succeeds");

        }catch (Exception e){

            LOGGER.info("Exit delete product in cart method");
            return APIResponse.fail(e.getMessage());
        }
    }

    @PostMapping(value = "/admin/addProductCart/{pid}/{productNum}")
    @ResponseBody
    public APIResponse addCartProduct(HttpServletRequest request,
                                      @PathVariable Integer pid,
                                      @PathVariable Integer productNum){
        LOGGER.info("Add product in cart method");

        try{
            iCartService.addProductInCart(pid,getCurrentUserId(request),productNum);

            LOGGER.info("Exit add product in cart method");
            return APIResponse.success("Add succeeds");

        }catch (Exception e){

            LOGGER.info("Exit add product in cart method");
            return APIResponse.fail(e.getMessage());
        }
    }

    @PostMapping(value = "/admin/changeQuantity")
    @ResponseBody
    public APIResponse updateQuantity(HttpServletRequest request,
                                      @RequestBody List<SimpleProduct> simpleProducts){
        LOGGER.info("Update product in cart method");

        try{
            for(SimpleProduct sp : simpleProducts){
                iCartService.updateProductInCart(sp.getId(),getCurrentUserId(request),sp.getQuantity());
            }

            LOGGER.info("Exit update product in cart method");
            return APIResponse.success("Update succeeds");

        }catch (Exception e){

            LOGGER.info("Exit update product in cart method");
            return APIResponse.fail(e.getMessage());
        }
    }

    static class SimpleProduct implements Serializable {
        private Integer id;
        private Integer quantity;
        SimpleProduct(){
        }

        SimpleProduct(Integer id,Integer quantity){
            this.id = id;
            this.quantity = quantity;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setQuantity(Integer quantity) {
            this.quantity = quantity;
        }

        public Integer getId() {
            return id;
        }

        public Integer getQuantity() {
            return quantity;
        }
    }

}
