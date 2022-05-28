package com.ycao.automobile.service;

import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.SystemDomain;

import java.util.List;
/**
 * @Type IShopService.java
 * @Desc shop service class
 * @author yuan.cao@utbm.fr
 * @date 27/05/2022 00:11
 * @version 1.0
 */
public interface IShopService {

    List<SystemDomain> getAllSystem();

    /**
     * get the number of the current active products
     * @return
     */
    Integer getAllProductsNum();

    /**
     * get the products which is of this sub-piece type
     * @param pdid the id of the sub-piece
     * @return
     */
    List<ProductDomain> getAllProductsWithPdid(Integer pdid);
}
