package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.nex.springboot.backend.quizz.models.entity.Card;

public interface ICardService {

	public List<Card> findAll();
	
	public List<Card> findAllByCategoryId(Long id);
	
	public Page<Card> findAll(Pageable pageable);
	
	public Card findById(Long id);
	
	public Card save(Card card);
	
	public void delete(Long id);
	
}
