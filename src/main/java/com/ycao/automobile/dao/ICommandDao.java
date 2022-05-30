package com.ycao.automobile.dao;

import com.ycao.automobile.model.command.CommandDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * @Type ICommandDao.java
 * @Desc command dao
 * @author yuan.cao@utbm.fr
 * @date 30/05/2022 23:31
 * @version 1.0
 */
public interface ICommandDao extends JpaRepository<CommandDomain,Integer> {

    /**
     * used the original method: save(Object obj) instead
     * @param id
     * @param status
     * @param content
     * @param orderday
     * @param uid
     * @param billingAddress
     * @param billingName
     * @param billingPostcode
     * @param billingEmail
     * @param billingPhone
     * @param shippingAddress
     * @param shippingName
     * @param shippingPostcode
     * @param orderComments
     * @return
     */
    @Deprecated
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="insert into t_command(" +
            "id," +
            "status," +
            "content," +
            "order_day," +
            "uid," +
            "billing_address," +
            "billing_billing_name," +
            "billing_postcode," +
            "billing_email," +
            "billing_phone," +
            "shipping_address," +
            "shipping_name," +
            "shipping_postcode," +
            "content) " +
            "values(:id,:status,:content,:orderDay,:uid,:billingAddress,:billingName,:billingPostcode,:billingEmail,:billingPhone,:shippingAddress,:shippingName,:shippingPostcode,:content) ")
    Integer passCommand(@Param("id") Integer id,
                        @Param("status") Integer status,
                        @Param("content") String content,
                        @Param("orderDay") String orderday,
                        @Param("uid") Integer uid,
                        @Param("billingAddress") String billingAddress,
                        @Param("billingName") String billingName,
                        @Param("billingPostcode") String billingPostcode,
                        @Param("billingEmail") String billingEmail,
                        @Param("billingPhone") String billingPhone,
                        @Param("shippingAddress") String shippingAddress,
                        @Param("shippingName") String shippingName,
                        @Param("shippingPostcode") String shippingPostcode,
                        @Param("content") String orderComments);


    /**
     * get all the commands, order by time
     * @param uid the id of user
     * @return
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_command c where c.dr=0 order by c.ts desc ")
    List<CommandDomain> getAllCommandFromUser(@Param(value = "uid") Integer uid);
}
