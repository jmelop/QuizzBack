package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.nex.springboot.backend.quizz.models.dao.ILanguageDao;
import com.nex.springboot.backend.quizz.models.entity.Language;

@Service
public class LanguageServiceImpl implements ILanguageService {
	
	@Autowired
	private ILanguageDao languageDao;

	@Override
	@Transactional(readOnly = true)
	public List<Language> findAll() {
		return (List<Language>) languageDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Language findById(Long id) {
		return languageDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Language save(Language language) {
		return languageDao.save(language);
	}

	@Override
	public void delete(Long id) {
		languageDao.deleteById(id);
	}

}
