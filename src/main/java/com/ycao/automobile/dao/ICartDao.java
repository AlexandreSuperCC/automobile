package com.ycao.automobile.dao;

import com.ycao.automobile.model.cart.CartDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

/**
 * @Type ICartDao.java
 * @Desc cart dao
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 17:01
 * @version 1.0
 */
public interface ICartDao extends JpaRepository<CartDomain,Integer> {

    /**
     * get the cart id of the user
     * @param uid the id of user
     * @return the id of cart
     */
    @Query(nativeQuery=true, value ="SELECT id from t_cart where uid=:uid ")
    Integer getCartIdOfUser(@Param("uid") Integer uid);

    /**
     * delete one product in cart, set dr = 1, so we can still restore it
     * @param pid the id the product
     * @param uid the id of the user
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="UPDATE t_cart c INNER JOIN t_cart_detail cd ON cd.cid = c.id SET cd.dr = 1,c.ts=now(),cd.ts=now() WHERE c.uid = :uid AND cd.pid = :pid ")
    int deleteProductInCart(@Param("pid") Integer pid,
                            @Param("uid") Integer uid);



    @Query(nativeQuery=true, value ="select cd.product_num from t_cart_detail cd inner join t_cart c on c.id = cd.cid where cd.pid=:pid and c.uid=:uid ")
    Integer getCurNumOfProdOfUser(@Param("uid") Integer uid,@Param("pid") Integer pid);


    /**
     * add one product in cart,
     * @param pid the id the product
     * @param uid the id of the user
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="insert into t_cart_detail(id,cid,pid,product_num,ts) values(:id,:cid,:pid,:product_num,NOW()) ")
    int addProductInCart(@Param("id") Integer id,
                         @Param("cid") Integer cid,
                         @Param("pid") Integer pid,
                         @Param("product_num") Integer productNum);


    /**
     * change the quantity of products in the cart
     * @param pid the id of product
     * @param uid the id of user
     * @param num the new quantity
     * @return
     */
    @Modifying
    @Transactional
    @Query(nativeQuery=true, value ="UPDATE t_cart c INNER JOIN t_cart_detail cd ON cd.cid = c.id SET cd.product_num = :num,c.ts=now(),cd.ts=now() WHERE c.uid = :uid AND cd.pid = :pid ")
    int updateQuantityProduct(@Param("pid") Integer pid,
                            @Param("uid") Integer uid,
                            @Param("num") Integer num);

}
