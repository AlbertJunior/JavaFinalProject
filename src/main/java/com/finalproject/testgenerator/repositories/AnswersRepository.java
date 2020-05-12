package com.finalproject.testgenerator.repositories;

import com.finalproject.testgenerator.models.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AnswersRepository extends JpaRepository<Answer, Integer> {
}