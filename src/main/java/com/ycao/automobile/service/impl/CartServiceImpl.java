package com.ycao.automobile.service.impl;

import com.ycao.automobile.dao.ICartDao;
import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.ICartService;
import com.ycao.automobile.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartServiceImpl implements ICartService {

    @Autowired
    IProductDao iProductDao;

    @Autowired
    ICartDao iCartDao;

    @Override
    @Cacheable(value="cartCaches",key="'cartCaches-'+#userId")
    public List<ProductDomain> getCartOfUser(Integer userId) {
        assert userId!=null;
        return iProductDao.getAllProductsInCart(userId);
    }

    @Override
    @CacheEvict(value="cartCaches",key="'cartCaches-'+#uid")
    public void deleteProductInCart(Integer pid, Integer uid) {
        iCartDao.deleteProductInCart(pid,uid);
    }

    @Override
    @CacheEvict(value="cartCaches",key="'cartCaches-'+#uid")
    public void addProductInCart(Integer pid, Integer uid, Integer productNum) {
        assert uid!=null&&pid!=null;
        Integer cid = iCartDao.getCartIdOfUser(uid);
        Integer curNum = iCartDao.getCurNumOfProdOfUser(uid,pid);
        if(curNum==null||curNum==0){//without this in cart
            iCartDao.addProductInCart(MyUtils.getRandomID(),cid,pid,productNum);

        }else{//cumulate
            iCartDao.updateQuantityProduct(pid,uid,curNum+productNum);
        }

    }

    @CacheEvict(value="cartCaches",key="'cartCaches-'+#uid")
    @Override
    public void updateProductInCart(Integer pid, Integer uid, Integer quantity) {
        iCartDao.updateQuantityProduct(pid, uid, quantity);
    }
}
