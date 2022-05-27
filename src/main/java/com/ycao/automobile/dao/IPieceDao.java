package com.ycao.automobile.dao;

import com.ycao.automobile.model.PieceDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPieceDao extends JpaRepository<PieceDomain,Integer> {
}
