package com.nex.springboot.backend.quizz.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardDao extends PagingAndSortingRepository<Card, Long>{
	
	@Query("select c from Card c where c.category.id=?1")
	public List<Card> findAllByCategoryId(Long id);

}
