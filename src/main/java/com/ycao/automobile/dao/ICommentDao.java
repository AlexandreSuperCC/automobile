package com.ycao.automobile.dao;

import com.ycao.automobile.model.CommentDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @Type ICommentDao.java
 * @Desc used for comment
 * @author yuan.cao@utbm.fr
 * @date 28/05/2022 22:34
 * @version 1.0
 */
public interface ICommentDao extends JpaRepository<CommentDomain,Integer> {

    @Query(nativeQuery=true, value ="SELECT * FROM t_comment where dr=0 and pid=:pid order by ts desc LIMIT 10 ")
    List<CommentDomain> getAllProductsComments(@Param(value = "pid") Integer pid);
}
