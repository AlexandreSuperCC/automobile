package com.ycao.automobile.service;

import com.ycao.automobile.model.ProductDomain;

import java.util.List;
/**
 * @Type ICartService.java
 * @Desc cart server
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 15:38
 * @version 1.0
 */
public interface ICartService {

    /**
     * get all the products in the cart of this user
     * @param userId user id 
     * @return all the products
     */
    List<ProductDomain> getCartOfUser(Integer userId);

    /**
     * delete one product in cart, can still restore it
     * @param pid the id of product
     * @param uid the id of user
     */
    void deleteProductInCart(Integer pid, Integer uid);

    /**
     * add one product in cart
     * @param pid the id of product
     * @param uid the id of user
     */
    void addProductInCart(Integer pid, Integer uid, Integer productNum);

    /**
     * update the quantity of the product in the cart
     * @param pid
     * @param uid
     * @param quantity
     */
    void updateProductInCart(Integer pid, Integer uid, Integer quantity);
}
