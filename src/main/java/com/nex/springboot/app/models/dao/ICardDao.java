package com.nex.springboot.app.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nex.springboot.app.models.entity.Card;

public interface ICardDao extends CrudRepository<Card, Long>{

}
