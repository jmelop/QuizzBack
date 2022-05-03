package com.nex.springboot.backend.quizz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nex.springboot.backend.quizz.models.entity.Category;
import com.nex.springboot.backend.quizz.models.services.ICategoryService;

@CrossOrigin(origins = { "https://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CategoryRestService {

	@Autowired
	private ICategoryService categoryService;

	@GetMapping("/categories")
	public List<Category> index() {
		return categoryService.findAll();
	}
	
}
