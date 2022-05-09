package com.nex.springboot.backend.quizz.controllers;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
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
	
	@GetMapping("/cards/cat/{id}")
	public List<Card> findAllByCategoryId(@PathVariable Long id) {
		return cardService.findAllByCategoryId(id);
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
	
	@RequestMapping(value = { "/cardss", "/" }, method = RequestMethod.GET)
	public ResponseEntity<?> list(@RequestParam(name = "page", defaultValue = "0") int page) {
		Map<String, Object> response = new HashMap<>();
		Pageable pageRequest = PageRequest.of(page, 4);
		Page<Card> cards = cardService.findAll(pageRequest);
		int totalPage = cards.getTotalPages();
		response.put("totalPages", totalPage);
		response.put("cards", cards.getContent());
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}

	@PostMapping("/cards")
	public ResponseEntity<?> create(@Valid @RequestBody Card card, BindingResult result) {
		Card newCard = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		try {
			newCard = cardService.save(card);
		} catch (DataAccessException e) {
			response.put("message", "Error insert new card");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "New card created");
		response.put("card", newCard);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}

	@PutMapping("/cards/{id}")
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<?> update(@Valid @RequestBody Card card, BindingResult result, @PathVariable Long id) {
		Card fetchedCard = cardService.findById(id);
		Card cardUpdated = null;
		Map<String, Object> response = new HashMap<>();
		if (result.hasErrors()) {
			List<String> errors = result.getFieldErrors().stream()
					.map(err -> "Field '" + err.getField() + "' " + err.getDefaultMessage())
					.collect(Collectors.toList());
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		if (fetchedCard == null) {
			response.put("message", "Client ID: ".concat(id.toString().concat(" does not exist in DB")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		try {
			cardUpdated = fetchedCard;
			cardUpdated.setGroup(card.getGroup());
			cardUpdated.setCategory(card.getCategory());
			cardUpdated.setSpanish(card.getSpanish());
			cardUpdated.setTranslation(card.getTranslation());
			cardUpdated.setLanguage(card.getLanguage());
			cardService.save(cardUpdated);
		} catch (DataAccessException e) {
			response.put("message", "Error updating card");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Updated card!");
		response.put("card", cardUpdated);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@DeleteMapping("/cards/{id}")
	public ResponseEntity<?> delete(@PathVariable Long id) {
		Map<String, Object> response = new HashMap<>();
		try {
			cardService.delete(id);
		} catch (DataAccessException e) {
			response.put("message", "Error deleting card!");
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("message", "Deleted card");
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
	}
}
