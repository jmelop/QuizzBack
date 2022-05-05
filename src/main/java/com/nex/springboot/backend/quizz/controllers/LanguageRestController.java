package com.nex.springboot.backend.quizz.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
	
	@PostMapping("/languages")
	public ResponseEntity<?> create(@Valid @RequestBody Language language, BindingResult result) {
		Language newLanguage = null;
		Map<String, Object> response = new HashMap<>();
		if(result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
		}
		try {
			newLanguage = languageService.save(language);
		} catch (DataAccessException e) {
			response.put("message", "Error insert new language");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "New language created");
		response.put("language", newLanguage);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
