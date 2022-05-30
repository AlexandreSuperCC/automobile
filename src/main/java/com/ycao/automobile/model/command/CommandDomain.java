package com.ycao.automobile.model.command;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_command")
public class CommandDomain {
    @Id
    private Integer id;

    @Column(name = "status")
    private Integer status;

    @Column(name = "content")
    private String content;

    @Column(name = "order_day")
    private String orderDay;

    @Column(name = "uid")
    private Integer uid;

    @Column(name = "billing_address")
    private String billingAddress;
    @Column(name = "billing_name")
    private String billingName;
    @Column(name = "billing_postcode")
    private String billingPostcode;
    @Column(name = "billing_email")
    private String billingEmail;
    @Column(name = "billing_phone")
    private String billingPhone;

    @Column(name = "shipping_address")
    private String shippingAddress;
    @Column(name = "shipping_name")
    private String shippingName;
    @Column(name = "shipping_postcode")
    private String shippingPostcode;

    @Column(name = "total_price")
    private String totalPrice;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "cid")
    private List<CommandDetailDomain> commandDetailDomains;

}
