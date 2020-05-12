package com.finalproject.testgenerator.repositories;

import com.finalproject.testgenerator.models.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionsRepository extends CrudRepository<Question, Integer> {
}

