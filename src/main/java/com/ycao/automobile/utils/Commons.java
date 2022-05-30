package com.ycao.automobile.utils;


import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.*;


/**
 * @Type Commons.java
 * @Desc common utils, mainly used in template
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 11:44
 * @version 1.0
 */
@Component
public class Commons {


    /**
     * 获取随机数
     *
     * @param max
     * @param str
     * @return
     */
    public static String random(int max, String str) {
        return UUID.random(1, max) + str;
    }

    public static String random(Long seed, int max, String str){
        if (seed == null)
            return random(max, str);
        Random random = new Random(seed);
        return random.nextInt(max) + str;
    }

    /**
     * get the price string with € removed it and get the price total of a product with the quantity
     * @param price the single price with €
     * @param quantity the number of the product
     * @return the total price witn €
     */
    public static String getTotalPrice(String price, String quantity){
        if(price==null||quantity==null) return "0 €";
        String str1 = price.trim();
        str1 = str1.replace(",",".");
        str1 = str1.substring(0,str1.length()-1);
        Double priceDouble = Double.parseDouble(str1);
        if(quantity.trim()==""||quantity=="null") return "0 €";
        Integer quantityInt = Integer.parseInt(quantity);
        Double res = priceDouble*quantityInt;
        BigDecimal valueDecimal = new BigDecimal(res);
        double value = valueDecimal.setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue();
        return value+" €";
    }

}
