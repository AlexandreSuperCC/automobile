package com.ycao.automobile.model.cart;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_cart")
public class CartDomain {
    @Id
    private Integer id;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "ts")
    private String ts;

    @Column(name = "dr")
    private Integer dr;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private List<CartDetailDomain> cartDetailDomainList;
}
