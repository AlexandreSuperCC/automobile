package com.ycao.automobile.dao;

import com.ycao.automobile.model.SubPieceDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ISubPieceDao extends JpaRepository<SubPieceDomain,Integer> {
}
