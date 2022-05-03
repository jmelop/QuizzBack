package com.nex.springboot.backend.quizz.models.services;

import java.util.List;

import com.nex.springboot.backend.quizz.models.entity.Category;

public interface ICategoryService {
	
	public List<Category> findAll();
	
	public Category findById(Long id);
	
	public Category save(Category category);
	
	public void delete(Long id);

}
