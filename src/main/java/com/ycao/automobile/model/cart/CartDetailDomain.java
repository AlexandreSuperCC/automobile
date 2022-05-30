package com.ycao.automobile.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_cart_detail")
public class CartDetailDomain {
    @Id
    private Integer id;

    @Column(name = "cid")
    private Integer cid;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "product_num")
    private Integer productNum;

    @Column(name = "ts")
    private String ts;

    @Column(name = "dr")
    private Integer dr;
}
