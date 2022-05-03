package com.nex.springboot.backend.quizz.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.nex.springboot.backend.quizz.models.entity.Category;

public interface ICategoryDao extends CrudRepository<Category, Long> {

}
