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
@Table(name = "t_comment")
public class CommentDomain {
    @Id
    private Integer id;

    @Column(name = "author_name")
    private String authorName;

    @Column(name = "content")
    private String content;

    @Column(name = "time")
    private String time;

    @Column(name = "purpose")
    private String purpose;

    @Column(name = "dr")
    private Integer dr;

    @Column(name = "ts")
    private String ts;

    @Column(name = "pid")
    private Integer pid;

    @Column(name = "rate")
    private String rate;
}
