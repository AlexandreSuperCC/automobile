package com.ycao.automobile.service;

import com.ycao.automobile.model.UserDomain;

/**
 * @Type IUserService.java
 * @Desc user service
 * @author yuan.cao@utbm.fr
 * @date 29/05/2022 14:06
 * @version 1.0
 */
public interface IUserService {



    /**
     * user login
     * @param username
     * @param password md5 encryption
     * @return
     */
    UserDomain login(String username, String password);

    /**
     * Get user information based on primary key number
     * @param uId
     * @return
     */
    UserDomain getUserInfoById(Integer id);
}
