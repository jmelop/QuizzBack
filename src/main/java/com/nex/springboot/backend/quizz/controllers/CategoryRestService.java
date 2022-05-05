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

	@PostMapping("/categories")
	public ResponseEntity<?> create(@Valid @RequestBody Category category, BindingResult result) {
		Category newCategory = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
		}
		try {
			newCategory = categoryService.save(category);
		} catch (DataAccessException e) {
			response.put("message", "Error insert new category");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "New category created");
		response.put("category", newCategory);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

}
