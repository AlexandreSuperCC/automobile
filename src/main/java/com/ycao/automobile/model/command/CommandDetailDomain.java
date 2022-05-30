package com.ycao.automobile.model.command;

import com.ycao.automobile.model.ProductDomain;

import javax.persistence.*;

@Entity
@Table(name = "t_command_detail")
public class CommandDetailDomain {
    @Id
    private Integer id;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "product_num")
    private Integer productNum;

    @Column(name = "cid")
    private Integer cid;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @OneToOne
    @JoinColumn(name="pid",referencedColumnName="id",insertable = false,updatable = false)
    private ProductDomain productDomain;

    public CommandDetailDomain() {
    }

    public CommandDetailDomain(Integer id, Integer pid, Integer productNum, Integer cid, Integer dr, String ts, ProductDomain productDomain) {
        this.id = id;
        this.pid = pid;
        this.productNum = productNum;
        this.cid = cid;
        this.dr = dr;
        this.ts = ts;
        this.productDomain = productDomain;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public Integer getProductNum() {
        return productNum;
    }

    public void setProductNum(Integer productNum) {
        this.productNum = productNum;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public Integer getDr() {
        return dr;
    }

    public void setDr(Integer dr) {
        this.dr = dr;
    }

    public String getTs() {
        return ts;
    }

    public void setTs(String ts) {
        this.ts = ts;
    }

    public ProductDomain getProductDomain() {
        return productDomain;
    }

    public void setProductDomain(ProductDomain productDomain) {
        this.productDomain = productDomain;
    }
}
