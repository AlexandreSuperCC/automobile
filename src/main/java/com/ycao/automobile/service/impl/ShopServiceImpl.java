package com.ycao.automobile.service.impl;

import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.dao.ISystemDao;
import com.ycao.automobile.model.SystemDomain;
import com.ycao.automobile.service.IShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ShopServiceImpl implements IShopService {

    @Autowired
    ISystemDao iSystemDao;

    @Autowired
    IProductDao iProductDao;

    @Override
    public List<SystemDomain> getAllSystem() {
        return iSystemDao.getAllSystem();
    }

    @Override
    public Integer getAllProductsNum() {
        return iProductDao.getAllProductsNum();
    }
}
