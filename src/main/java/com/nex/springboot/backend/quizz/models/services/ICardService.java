package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardService {

	public List<Card> findAll();
	
	public Card findById(Long id);

}
