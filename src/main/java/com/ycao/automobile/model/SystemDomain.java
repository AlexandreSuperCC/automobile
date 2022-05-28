package com.ycao.automobile.model;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "t_systeme")
public class SystemDomain {

    private static final long serialVersionUID = 1l;


    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "sid")
    private List<PieceDomain> pieceDomainList;

    public SystemDomain() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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

    public List<PieceDomain> getPieceDomainList() {
        return pieceDomainList;
    }

    public void setPieceDomainList(List<PieceDomain> pieceDomainList) {
        this.pieceDomainList = pieceDomainList;
    }
}
