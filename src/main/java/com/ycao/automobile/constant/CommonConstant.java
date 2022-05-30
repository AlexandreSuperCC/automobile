package com.ycao.automobile.constant;
/**
 * @Type CommonConstant.java
 * @Desc common constant
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 14:06
 * @version 1.0
 */
public interface CommonConstant {

    class Web {
        /**
         * session's key
         */
        public static final String LOGIN_SESSION_KEY = "login_user";
        /**
         * should be 16 bytes or otherwise:
         * java.security.InvalidKeyException: Invalid AES key length: 8 bytes
         */
        public static final String AES_SALT = "0123456789abcdef";
        public static final String USER_IN_COOKIE = "S_L_ID";
    }

}
