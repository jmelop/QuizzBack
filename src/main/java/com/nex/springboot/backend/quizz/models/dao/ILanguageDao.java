package com.nex.springboot.backend.quizz.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nex.springboot.backend.quizz.models.entity.Language;

public interface ILanguageDao extends CrudRepository<Language, Long> {

}
