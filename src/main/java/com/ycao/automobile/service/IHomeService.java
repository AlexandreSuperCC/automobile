package com.ycao.automobile.service;

import com.ycao.automobile.model.ProductDomain;

import java.util.List;
/**
 * @Type IHomeService.java
 * @Desc home service class
 * @author yuan.cao@utbm.fr
 * @date 26/05/2022 12:08
 * @version 1.0
 */
public interface IHomeService {
    /**
     * get high note product with comments
     * @param limitArg the limit array
     * @return
     */
    List<ProductDomain> getHighNoteProductHasComments(Integer... limitArg);
    /**
     * get latest product with comments
     * @param limitArg the limit array
     * @return
     */
    List<ProductDomain> getLatestProductHasComments(Integer... limitArg);
    /**
     * get top sold-well products
     * @param limitArg the limit array
     * @return
     */
    List<ProductDomain> getBestSeller(Integer... limitArg);
}
