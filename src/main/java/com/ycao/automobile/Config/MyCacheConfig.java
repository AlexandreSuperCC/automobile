package com.ycao.automobile.Config;

import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.Method;
import java.util.Arrays;

/**
 * @Type MyCacheConfig.java
 * @Desc used for generate the key of the cache in the server
 * @author yuan.cao@utbm.fr
 * @date 28/05/2022 15:10
 * @version 1.0
 */
@Configuration
public class MyCacheConfig {
    @Bean("myKeyGenerator")
    public KeyGenerator keyGenerator(){
        return new KeyGenerator(){
            @Override
            public Object generate(Object o, Method method, Object... objects) {
                return method.getName()+"["+ Arrays.asList(objects.toString())+"]";
            }
        };
    }
}
