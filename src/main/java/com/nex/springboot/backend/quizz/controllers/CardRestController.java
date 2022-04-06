package com.nex.springboot.backend.quizz.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.nex.springboot.backend.quizz.models.entity.Card;
import com.nex.springboot.backend.quizz.models.services.ICardService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class CardRestController {

	@Autowired
	private ICardService cardService;

	@GetMapping("/cards")
	public List<Card> index() {
		return cardService.findAll();
	}

	@GetMapping("/cards/{id}")
	public ResponseEntity<?> findById(@PathVariable Long id) {
		Card card = null;
		Map<String, Object> response = new HashMap<>();
		try {
			card = cardService.findById(id);
		} catch (DataAccessException e) {
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		if (card == null) {
			response.put("message", "Card ID:".concat(id.toString().concat(" does not exist in DB")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		return new ResponseEntity<Card>(card, HttpStatus.OK);
	}

}
