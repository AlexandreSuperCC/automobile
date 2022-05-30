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
@Table(name = "t_user")
public class UserDomain {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;


    @Column(name = "password")
    private String password;

    @Column(name = "mail")
    private String mail;

    @Column(name = "address")
    private String address;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;
}
