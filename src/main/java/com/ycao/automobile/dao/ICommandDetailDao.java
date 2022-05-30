package com.ycao.automobile.dao;

import com.ycao.automobile.model.command.CommandDetailDomain;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ICommandDetailDao extends JpaRepository<CommandDetailDomain,Integer> {
}
