package com.ycao.automobile.model;

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
@Table(name = "t_produit")
public class ProductDomain {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "price")
    private String price;

    @Column(name = "status")
    private Integer status;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @Column(name = "pdid")
    private Integer pdid;

    @Column(name = "marque")
    private String marque;

    @Column(name = "rate")
    private String rate;

    @Column(name = "vdef1")
    private String vdef1;

    @Column(name = "vdef2")
    private Integer vdef2;

}
