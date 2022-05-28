package com.ycao.automobile.controller.home;

import com.ycao.automobile.controller.BaseController;
import com.ycao.automobile.model.CommentDomain;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class SingleProductController extends BaseController {

    @Autowired
    IProductService iProductService;

    @GetMapping(value = "/product/{id}")
    public String enterSingleProduct(HttpServletRequest request,
                                     @PathVariable Integer id){
        LOGGER.info("Enter single-product method");

        ProductDomain productDomain = iProductService.getProductInfo(id);
        String productName = productDomain.getName();
        Integer productId = productDomain.getId();
        Integer pdid = productDomain.getPdid();

        Map<String,Object> catNameMap = iProductService.getCategoryMap(id);
        List<ProductDomain> relatedProducts = iProductService.getRelatedProducts(productName,productId,6);
        List<ProductDomain> highNoteProducts = iProductService.getHighNoteProductSameCat(pdid,4);
        List<ProductDomain> latestProducts = iProductService.getLatestProductSameCat(pdid,5);
        List<CommentDomain> productComments = iProductService.getAllComments(productId);
        Integer commentsNum = productComments.size();

        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("highNoteProducts", highNoteProducts);
        request.setAttribute("relatedProducts", relatedProducts);
        request.setAttribute("productInfo", productDomain);
        request.setAttribute("catNameMap", catNameMap);
        request.setAttribute("productComments", productComments);
        request.setAttribute("commentsNum", commentsNum);

        LOGGER.info("Exit single-product method");
        return "home/single-product";
    }

    /**
     * click the single-product button in the index, show default product
     * @param request
     * @param id
     * @return
     */
    @GetMapping(value = {"/single-product"})
    public String clickEnterSingleProduct(HttpServletRequest request){

        ProductDomain productDomain = iProductService.getBestProduct();
        String productName = productDomain.getName();
        Integer productId = productDomain.getId();
        Integer pdid = productDomain.getPdid();

        Map<String,Object> catNameMap = iProductService.getCategoryMap(productId);
        List<ProductDomain> relatedProducts = iProductService.getRelatedProducts(productName,productId,6);
        List<ProductDomain> highNoteProducts = iProductService.getHighNoteProductSameCat(pdid,4);
        List<ProductDomain> latestProducts = iProductService.getLatestProductSameCat(pdid,5);
        List<CommentDomain> productComments = iProductService.getAllComments(productId);
        Integer commentsNum = productComments.size();

        request.setAttribute("latestProducts", latestProducts);
        request.setAttribute("highNoteProducts", highNoteProducts);
        request.setAttribute("relatedProducts", relatedProducts);
        request.setAttribute("catNameMap", catNameMap);
        request.setAttribute("productInfo", productDomain);
        request.setAttribute("productComments", productComments);
        request.setAttribute("commentsNum", commentsNum);

        return "home/single-product";
    }

    @PostMapping("/searchProduct")
    public String searchProduct(String productName){
        List<ProductDomain> relatedProducts = iProductService.getRelatedProducts(productName,-1,1);
        ProductDomain searchRes;
        if(relatedProducts.size()==0){
            searchRes = iProductService.getBestProduct();
        }else{
            searchRes = relatedProducts.toArray(new ProductDomain[0])[0];
        }

        return "redirect:/product/"+searchRes.getId();
    }

}
