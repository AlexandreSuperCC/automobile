package com.ycao.automobile.service.impl;

import com.ycao.automobile.dao.ICommandDao;
import com.ycao.automobile.dao.ICommandDetailDao;
import com.ycao.automobile.dao.IProductDao;
import com.ycao.automobile.model.ProductDomain;
import com.ycao.automobile.model.command.CommandDetailDomain;
import com.ycao.automobile.model.command.CommandDomain;
import com.ycao.automobile.service.ICartService;
import com.ycao.automobile.service.ICommandService;
import com.ycao.automobile.utils.Commons;
import com.ycao.automobile.utils.MyUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Service
public class CommandServiceImpl implements ICommandService {
    @Autowired
    ICommandDao iCommandDao;
    @Autowired
    ICommandDetailDao iCommandDetailDao;

    @Autowired
    ICartService iCartService;

    @Autowired
    IProductDao iProductDao;

    @Override
    @Transactional(rollbackFor=Exception.class)
    public void saveCommand(Integer uid, String billing_country, String billing_first_name, String billing_last_name, String billing_company, String billing_address_1, String billing_address_2, String billing_city, String billing_state, String billing_postcode, String billing_email, String billing_phone, String ship_to_different_address, String shipping_country, String shipping_first_name, String shipping_last_name, String shipping_company, String shipping_address_1, String shipping_address_2, String shipping_city, String shipping_state, String shipping_postcode, String order_comments) {

        String nowTime = MyUtils.getNowTimeString();

        /**/
        Integer commandId = MyUtils.getRandomID();
        CommandDomain commandDomain = new CommandDomain();
        commandDomain.setId(commandId);
        commandDomain.setStatus(0);
        commandDomain.setContent(order_comments);
        // 2022-05-25 12:07:16
        commandDomain.setOrderDay(nowTime);
        commandDomain.setTs(nowTime);
        commandDomain.setUid(uid);
        commandDomain.setDr(0);

        String billingAddress = (billing_company+" "+billing_address_1+" "+billing_address_2+" "+billing_city+" "+billing_state+" "+billing_country).trim();
        commandDomain.setBillingAddress(billingAddress);

        String billingName = billing_first_name+" "+billing_last_name!=null?billing_last_name.toUpperCase():"";
        commandDomain.setBillingName(billingName);
        commandDomain.setBillingPostcode(billing_postcode);
        commandDomain.setBillingEmail(billing_email);
        commandDomain.setBillingPhone(billing_phone);

        if(ship_to_different_address!=null){
            String shippingAddress = (shipping_company+" "+shipping_address_1+" "+shipping_address_2+" "+shipping_city+" "+shipping_state+" "+shipping_country).trim();
            commandDomain.setShippingAddress(shippingAddress);
            commandDomain.setShippingName(shipping_first_name+" "+shipping_last_name!=null?shipping_last_name.toUpperCase():"");
            commandDomain.setShippingPostcode(shipping_postcode);
        }else{
            commandDomain.setShippingAddress(billingAddress);
            commandDomain.setShippingName(billingName);
            commandDomain.setShippingPostcode(billing_postcode);
        }
        /**/

        List<ProductDomain> productDomains = iCartService.getCartOfUser(uid);
        for(ProductDomain product:productDomains){
            CommandDetailDomain commandDetail = new CommandDetailDomain();
            commandDetail.setId(MyUtils.getRandomID());
            commandDetail.setPid(product.getId());
            commandDetail.setProductNum(product.getVdef2());
            commandDetail.setCid(commandId);
            commandDetail.setTs(nowTime);
            commandDetail.setDr(0);
            iCommandDetailDao.save(commandDetail);
        }
        commandDomain.setTotalPrice(getTotalPrice(productDomains)+" €");
        iCommandDao.save(commandDomain);
    }

    @Override
    public List<CommandDomain> getAllCommandFromUser(Integer uid) {
        return iCommandDao.getAllCommandFromUser(uid);
    }


    private Double getTotalPrice(List<ProductDomain> productDomains){
        Double res=0.0;
        for (ProductDomain product:productDomains){
            String singlePrice = Commons.getTotalPrice(String.valueOf(product.getPrice()),String.valueOf(product.getVdef2()));
            Double sp = Double.parseDouble(singlePrice.substring(0,singlePrice.length()-1));
            res+=sp;
        }
        return res;
    }
}
