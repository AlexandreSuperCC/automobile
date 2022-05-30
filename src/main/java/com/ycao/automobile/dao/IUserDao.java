package com.ycao.automobile.dao;

import com.ycao.automobile.model.UserDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

/**
 * @Type IUserDao.java
 * @Desc user dao
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 12:30
 * @version 1.0
 */
public interface IUserDao  extends JpaRepository<UserDomain,Integer> {

    /**
     * used for login
     * @param name the name of user
     * @param password password with md5 encryption
     * @return
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_user where name=:name and password=:password ")
    UserDomain getUserInfoByCond(@Param(value = "name") String name, @Param(value = "password") String password);

    @Query(nativeQuery=true, value ="SELECT * FROM t_user where id=:id ")
    UserDomain getUserInfoById(@Param(value = "id") Integer id);

}
