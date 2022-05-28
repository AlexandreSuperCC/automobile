package com.ycao.automobile.service;

import com.ycao.automobile.model.CommentDomain;
import com.ycao.automobile.model.ProductDomain;

import java.util.List;
import java.util.Map;

/**
 * @Type IProductService.java
 * @Desc used for single-product.html
 * @author yuan.cao@utbm.fr
 * @date 28/05/2022 15:36
 * @version 1.0
 */
public interface IProductService {

    /**
     * get the info of one product
     * @param id the id of product
     * @return ProductDomain
     */
    ProductDomain getProductInfo(Integer id);

    ProductDomain getBestProduct();

    Map<String,Object> getCategoryMap(Integer id);

    /**
     * get the product which name includes the given name, excludes itself
     * @param name
     * @param id the id of the given product
     * @param limitArg
     * @return
     */
    List<ProductDomain> getRelatedProducts(String name,Integer id,Integer... limitArg);

    /**
     * get high note product with comments and in the same category
     * @param limitArg the limit array
     * @return
     */
    List<ProductDomain> getHighNoteProductSameCat(Integer id,Integer... limitArg);
    /**
     * get latest product with comments and in the same category
     * @param limitArg the limit array
     * @return
     */
    List<ProductDomain> getLatestProductSameCat(Integer id,Integer... limitArg);

    /**
     * get the 10 latest comments of a product
     * @param pid the id of the product
     * @return ProductDomain
     */
    List<CommentDomain> getAllComments(Integer pid);
}
