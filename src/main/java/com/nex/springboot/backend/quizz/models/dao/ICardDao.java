package com.nex.springboot.backend.quizz.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardDao extends CrudRepository<Card, Long>{
	
	@Query("select c from Card c where c.category.id=?1")
	public List<Card> findAllByCategoryId(Long id);

}
