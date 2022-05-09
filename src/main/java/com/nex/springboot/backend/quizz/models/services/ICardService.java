package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardService {

	public List<Card> findAll();
	
	public List<Card> findAllByCategoryId(Long id);
	
	public Card findById(Long id);
	
	public Card save(Card card);
	
	public void delete(Long id);
	
}
