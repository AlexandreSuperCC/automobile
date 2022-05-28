package com.ycao.automobile.service.impl;

import com.ycao.automobile.constant.ErrorConstant;
import com.ycao.automobile.dao.ICommentDao;
import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.exception.BusinessException;
import com.ycao.automobile.model.CommentDomain;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.service.IProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class ProductServiceImpl implements IProductService {

    @Autowired
    IProductDao iProductDao;
    @Autowired
    ICommentDao iCommentDao;

    @Override
    public ProductDomain getProductInfo(Integer id) {
        assert id!=null;
        return iProductDao.getSingleProduct(id);
    }

    @Override
    public ProductDomain getBestProduct() {
        return iProductDao.getBestProduct();
    }

    @Override
    public Map<String, Object> getCategoryMap(Integer id) {
        return iProductDao.findCatNameByProductId(id);
    }

    @Override
    public List<ProductDomain> getRelatedProducts(String name,Integer id,Integer... limitArg) {
        if(limitArg==null||limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getRelatedProducts(name,id,0,limitArg[0]);
        }else{
            productDomains = iProductDao.getRelatedProducts(name,id,limitArg[0],limitArg[1]);
        }
        return productDomains;
    }

    @Override
    public List<ProductDomain> getHighNoteProductSameCat(Integer pdid, Integer... limitArg) {
        assert pdid!=null;
        if(limitArg==null||limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getRateProductSameCat(pdid,0,limitArg[0]);
        }else{
            productDomains = iProductDao.getRateProductSameCat(pdid,limitArg[0],limitArg[1]);
        }
        return productDomains;
    }

    @Override
    public List<ProductDomain> getLatestProductSameCat(Integer pdid, Integer... limitArg) {
        assert pdid!=null;
        if(limitArg==null||limitArg.length>2){
            throw BusinessException.withErrorCode(ErrorConstant.Common.PARAM_NOT_VALID);
        }
        List<ProductDomain> productDomains;
        if (limitArg.length==1){
            productDomains = iProductDao.getLatestProductSameCat(pdid,0,limitArg[0]);
        }else{
            productDomains = iProductDao.getLatestProductSameCat(pdid,limitArg[0],limitArg[1]);
        }
        return productDomains;
    }

    @Override
    public List<CommentDomain> getAllComments(Integer pid) {
        assert pid!=null;
        return iCommentDao.getAllProductsComments(pid);
    }
}
