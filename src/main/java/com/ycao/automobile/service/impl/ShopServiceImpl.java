package com.ycao.automobile.service.impl;

import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.dao.ISystemDao;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.SystemDomain;
import com.ycao.automobile.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    ISystemDao iSystemDao;

    @Autowired
    IProductDao iProductDao;

    @Override
    @Cacheable(cacheNames = "allSystemCaches",key = "#root.methodName")
    public List<SystemDomain> getAllSystem() {
        return iSystemDao.getAllSystem();
    }

    @Override
    @Cacheable(cacheNames = "productsNumCaches",key = "#root.methodName")
    public Integer getAllProductsNum() {
        return iProductDao.getAllProductsNum();
    }

    @Override
    @Cacheable(cacheNames = "productsPidCaches",key = "#root.methodName+':['+#pdid+']'")
    public List<ProductDomain> getAllProductsWithPdid(Integer pdid) {
        assert pdid!=null;
        return iProductDao.getAllProductsWithPdid(pdid);
    }
}
