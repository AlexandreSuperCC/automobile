package com.ycao.automobile.dao;

import com.ycao.automobile.model.ProductDomain;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface IProductDao extends JpaRepository<ProductDomain,Integer> {

    /**
     * get high rate product who has comments
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.dr=0 and p.id in (select pid from t_comment where dr=0) ORDER BY p.rate desc, p.ts desc limit :start,:num ")
    List<ProductDomain> getRateProduct(@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get latest product who has comments
     * @param start where starts, includes it
     * @param num how many products are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.dr=0 and p.id in (select pid from t_comment where dr=0) ORDER BY p.ts desc, p.rate desc limit :start,:num ")
    List<ProductDomain> getLatestProduct(@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get latest product who has comments come first
     * @param start where starts, includes it
     * @param num how many products are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT p.* FROM t_produit p LEFT JOIN ( SELECT c.pid, count(*) num FROM t_comment c WHERE c.dr = 0 GROUP BY c.pid ) c ON p.id = c.pid WHERE  p.dr = 0 ORDER BY c.num DESC,p.ts DESC,p.rate DESC limit :start,:num ")
    List<ProductDomain> getLatestProductCommentsFirst(@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get best seller, we sold one product, we add one comment, no matter there is text or not,
     * so connect with t_comment
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT p.* FROM t_produit p INNER JOIN ( SELECT pid, count(*) ct FROM t_comment WHERE dr = 0 GROUP BY pid ) c ON c.pid = p.id WHERE p.dr = 0 ORDER BY c.ct DESC LIMIT :start,:num ")
    List<ProductDomain> getBestSeller(@Param(value = "start") int start, @Param(value = "num") int num);

    @Query(nativeQuery=true, value ="SELECT count(*) from t_produit where dr = 0 ")
    Integer getAllProductsNum();

    @Query(nativeQuery=true, value ="SELECT * from t_produit where pdid=:pdid and dr = 0 ")
    List<ProductDomain> getAllProductsWithPdid(@Param(value = "pdid") Integer pdid);

    @Query(nativeQuery=true, value ="SELECT * from t_produit where id=:id ")
    ProductDomain getSingleProduct(@Param(value = "id") Integer id);

    @Query(nativeQuery=true, value ="SELECT * from t_produit where dr=0 and status=0 order by rate desc, ts desc limit 1 ")
    ProductDomain getBestProduct();

    @Query(nativeQuery=true, value ="SELECT s.NAME sname, p.NAME pname, pd.NAME pdname FROM t_systeme s INNER JOIN t_piece p ON s.id = p.sid INNER JOIN t_piece_detaillee pd ON pd.pid = p.id INNER JOIN t_produit pro ON pro.pdid = pd.id WHERE pro.id = :id ")
    Map<String,Object> findCatNameByProductId(@Param(value = "id") Integer id);

    @Query(nativeQuery=true, value ="SELECT * from t_produit where id!=:id and dr = 0 and name like %:name% ORDER BY rate desc, ts desc limit :start,:num")
    List<ProductDomain> getRelatedProducts(@Param(value = "name") String name,@Param(value = "id") int id,@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get high rate product who has comments and in the same category
     * @param start where starts, includes it
     * @param num how many comments are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.dr=0 and p.pdid=:pdid ORDER BY p.rate desc, p.ts desc limit :start,:num ")
    List<ProductDomain> getRateProductSameCat(@Param(value = "pdid") int pdid,@Param(value = "start") int start, @Param(value = "num") int num);

    /**
     * get latest product who has comments and in the same category
     * @param start where starts, includes it
     * @param num how many products are wanted
     * @return ProductDomain
     */
    @Query(nativeQuery=true, value ="SELECT * FROM t_produit p where p.dr=0 and p.pdid=:pdid ORDER BY p.ts desc, p.rate desc limit :start,:num ")
    List<ProductDomain> getLatestProductSameCat(@Param(value = "pdid") int pdid, @Param(value = "start") int start, @Param(value = "num") int num);


    /**
     * get all the products in one's cart, attention: we use vdef2 to stock the amount of this product
     * @param uid the id of the user
     * @return
     */
    @Query(nativeQuery=true, value ="SELECT p.id, p.NAME, p.img_url, p.price, p.status, p.dr, p.ts, p.pdid, p.marque, p.rate, p.vdef1, cd.product_num vdef2 FROM t_cart c INNER JOIN t_cart_detail cd ON c.id = cd.cid INNER JOIN t_produit p ON p.id = cd.pid INNER JOIN t_user u ON u.id = c.uid WHERE c.dr = 0 AND cd.dr = 0 AND u.id = :uid ORDER BY c.ts DESC ")
    List<ProductDomain> getAllProductsInCart(@Param(value = "uid") int uid);

}
