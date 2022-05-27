package com.ycao.automobile.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "t_piece")
public class PieceDomain {
    @Id
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @Column(name = "sid")
    private Integer sid;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name = "pid")
    private List<SubPieceDomain> subPieceDomainList;
}
