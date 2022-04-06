package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nex.springboot.backend.quizz.models.dao.ICardDao;
import com.nex.springboot.backend.quizz.models.entity.Card;

@Service
public class ClientServiceImpl implements ICardService{
	
	@Autowired
	private ICardDao cardDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Card> findAll() {
		return (List<Card>) cardDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Card findById(Long id) {
		return cardDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Card save(Card card) {
		return cardDao.save(card);
	}

	@Override
	public void delete(Long id) {
		cardDao.deleteById(id);
	}

}
