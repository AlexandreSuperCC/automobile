package com.ycao.automobile.dao;

import com.ycao.automobile.model.SystemDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
/**
 * @Type ISystemDao.java
 * @Desc system dao
 * @author yuan.cao@utbm.fr
 * @date 26/05/2022 22:47
 * @version 1.0
 */
public interface ISystemDao extends JpaRepository<SystemDomain,Integer> {


    /**
     * get all active systems with related pieces and sub-pieces
     * @return systems list
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_systeme s where s.dr=0 order by s.name ")
    List<SystemDomain> getAllSystem();

}
