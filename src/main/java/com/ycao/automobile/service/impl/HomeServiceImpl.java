package com.ycao.automobile.service.impl;

import com.ycao.automobile.constant.ErrorConstant;
import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.exception.BusinessException;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.IHomeService;
import com.ycao.automobile.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class HomeServiceImpl implements IHomeService {

    @Autowired
    IProductDao iProductDao;

    @Override
    @Cacheable(cacheNames = "highNoteProductsCaches",keyGenerator="myKeyGenerator")
    public List<ProductDomain> getHighNoteProductHasComments(Integer... limitArg) {
        if(limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getRateProduct(0,limitArg[0]);
        }else{
            productDomains = iProductDao.getRateProduct(limitArg[0],limitArg[1]);
        }
        productDomains.forEach(e ->
                e.setVdef2(MyUtils.strToInt(e.getRate())));
        return productDomains;
    }

    @Override
    @Cacheable(cacheNames = "latestProductsCCaches",keyGenerator="myKeyGenerator")
    public List<ProductDomain> getLatestProductHasComments(Integer... limitArg) {
        if(limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getLatestProduct(0,limitArg[0]);
        }else{
            productDomains = iProductDao.getLatestProduct(limitArg[0],limitArg[1]);
        }
        productDomains.forEach(e ->{
            if(e.getRate()!=null){
                e.setVdef2(MyUtils.strToInt(e.getRate()));
            }});
        return productDomains;
    }

    @Override
    @Cacheable(cacheNames = "bestSellersCaches",keyGenerator="myKeyGenerator")
    public List<ProductDomain> getBestSeller(Integer... limitArg){
        if(limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getBestSeller(0,limitArg[0]);
        }else{
            productDomains = iProductDao.getBestSeller(0,limitArg[0]);
        }
        productDomains.forEach(e ->
                e.setVdef2(MyUtils.strToInt(e.getRate())));
        return productDomains;
    }

    @Override
    @Cacheable(cacheNames = "latestProductsCFCaches",keyGenerator="myKeyGenerator")
    public List<ProductDomain> getLatestProductCommentsPriority(Integer... limitArg) {
        if(limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getLatestProductCommentsFirst(0,limitArg[0]);
        }else{
            productDomains = iProductDao.getLatestProductCommentsFirst(limitArg[0],limitArg[1]);
        }
        return productDomains;
    }
}
