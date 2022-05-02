package com.nex.springboot.backend.quizz.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nex.springboot.backend.quizz.models.entity.Language;
import com.nex.springboot.backend.quizz.models.services.ILanguageService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class LanguageRestController {
	
	@Autowired
	private ILanguageService languageService;
	
	@GetMapping("/languages")
	public List<Language> index() {
		return languageService.findAll();
	}

}
