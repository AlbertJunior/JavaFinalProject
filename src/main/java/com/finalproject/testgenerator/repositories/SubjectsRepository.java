package com.finalproject.testgenerator.repositories;

import com.finalproject.testgenerator.models.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SubjectsRepository extends JpaRepository<Subject, Integer> {
}
