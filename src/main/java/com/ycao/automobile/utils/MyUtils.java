package com.ycao.automobile.utils;

/**
 * @Type MyUtils.java
 * @Desc methods frequent used
 * @author yuan.cao@utbm.fr
 * @date 26/05/2022 15:39
 * @version 1.0
 */
public class MyUtils {

    public static Integer strToInt(String str){
        assert str!=null;
        Double db = Double.valueOf(str);
        Long dbR = Math.round(db);
        return new Long(dbR).intValue();
    }
}
