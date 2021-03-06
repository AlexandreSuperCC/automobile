package com.ycao.automobile;

import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.dao.ISystemDao;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.SystemDomain;
import com.ycao.automobile.utils.Commons;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class AutomobileApplicationTests {

    @Test
    void contextLoads() {
        Double db = Double.valueOf("2.25");
        Double dbR = Math.floor(db);
        System.out.println(new Double("2.9").intValue());
    }

    @Autowired
    ISystemDao iSystemDao;

    @Autowired
    IProductDao iProductDao;

    @Test
    void hibernateTest() {
        List<SystemDomain> systemDomains = iSystemDao.getAllSystem();
        System.out.println(systemDomains);
        System.out.println("ok");
    }

    @Test
    void productTest() {
        List<ProductDomain> productDomains = iProductDao.getRelatedProducts("frein",0,0,5);
        System.out.println(productDomains);
        System.out.println("ok");
    }

    @Test
    void priceTest() {
        String price = Commons.getTotalPrice("42,50 € ","10");
        System.out.println(price);
        System.out.println("ok");
    }
}
