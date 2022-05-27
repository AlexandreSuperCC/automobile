package com.ycao.automobile.dao;

import com.ycao.automobile.model.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface IProductDao extends JpaRepository<ProductDomain,Integer> {

    /**
     * get high rate product who has comments
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.status=0 and p.dr=0 and p.id in (select pid from t_comment where dr=0) ORDER BY p.rate desc, p.ts desc limit :start,:num ")
    List<ProductDomain> getRateProduct(@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get latest product who has comments
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.status=0 and p.dr=0 and p.id in (select pid from t_comment where dr=0) ORDER BY p.ts desc, p.rate desc limit :start,:num ")
    List<ProductDomain> getLatestProduct(@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get best seller, we sold one product, we add one comment, no matter there is text or not,
     * so connect with t_comment
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT p.* FROM t_produit p RIGHT JOIN t_comment c ON c.pid = p.id WHERE p.status=0 and p.dr = 0 and c.dr=0 GROUP BY p.NAME ORDER BY count(*) DESC LIMIT :start,:num")
    List<ProductDomain> getBestSeller(@Param(value = "start") int start, @Param(value = "num") int num);

    @Query(nativeQuery=true, value ="SELECT count(*) from t_produit where dr = 0 ")
    Integer getAllProductsNum();
}
