package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import com.nex.springboot.backend.quizz.models.entity.Language;

public interface ILanguageService {
	
	public List<Language> findAll();
	
	public Language findById(Long id);
	
	public Language save(Language language);
	
	public void delete(Long id);
	
}
