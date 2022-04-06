package com.nex.springboot.backend.quizz.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardDao extends CrudRepository<Card, Long>{

}
