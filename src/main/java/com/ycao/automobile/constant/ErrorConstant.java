package com.ycao.automobile.constant;

/**
 * @Type ErrorConstant.java
 * @Desc error constant
 * @author yuan.cao@utbm.fr
 * @date 01/05/2022 13:41
 * @version 1.0
 */
public interface ErrorConstant {
    class Common {
        public static final String PARAM_IS_EMPTY = "the parameter is empty";
        public static final String PARAM_NOT_VALID = "numbers of parameters not valid";
    }
    class Security {
        public static final String JWT_UNAUTHORIZED = "wrong token";
    }
    class Login {
        public static final String LOGIN_USERNAME_EMPTY = "username is empty";
        public static final String USERNAME_PASSWORD_ERROR = "username doesn't exist or password is incorrect";
        public static final String OLD_PASSWORD_ERROR = "old password is incorrect";
        public static final String MAKE_TOKEN_ERROR = "making token fails";
    }
    class HttpStatus {
        public final static Integer NO_TOKEN = 410;
        public final static Integer JWT_UNAUTHORIZED = 412;
        public final static Integer RESOURCE_UNAUTHORIZED = 401;
    }
    class Atth {
        public final static String UPLOAD_FILE_FAIL = "uploading attachment fails";

    }
}
