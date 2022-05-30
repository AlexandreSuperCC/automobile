package com.ycao.automobile.service.impl;

import com.ycao.automobile.constant.ErrorConstant;
import com.ycao.automobile.dao.IUserDao;
import com.ycao.automobile.exception.BusinessException;
import com.ycao.automobile.model.UserDomain;
import com.ycao.automobile.service.IUserService;
import com.ycao.automobile.utils.security.SecutiryUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    IUserDao iUserDao;

    @Override
    public UserDomain login(String username, String password) {
        if (StringUtils.isBlank(username) || StringUtils.isBlank(password))
            throw BusinessException.withErrorCode(ErrorConstant.Login.LOGIN_USERNAME_EMPTY);

        String pwd = SecutiryUtils.MD5encode(username + password);
        UserDomain user = iUserDao.getUserInfoByCond(username, pwd);
        if (null == user)
            throw BusinessException.withErrorCode(ErrorConstant.Login.USERNAME_PASSWORD_ERROR);

        return user;
    }

    @Override
    public UserDomain getUserInfoById(Integer id) {
        return iUserDao.getUserInfoById(id);
    }
}
