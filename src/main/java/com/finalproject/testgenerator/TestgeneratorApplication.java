package com.finalproject.testgenerator;

import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.repositories.AnswersRepository;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TestgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestgeneratorApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(QuestionsRepository repository, SubjectsRepository subjectsRepository, AnswersRepository answersRepository){
		return args -> {
			answersRepository.save(new Answer("raspuns1", 1));
			answersRepository.save(new Answer("raspuns2", 0));
			Subject subject = new Subject("matematica");
			subjectsRepository.save(subject);

			repository.save(new Question("tu?", 3, 5, subject));
			repository.save(new Question("da???", 3, 5, subject));
			repository.save(new Question("ce faci?", 2, 4, subject));
			repository.save(new Question("tu?", 2, 4, subject));
			repository.save(new Question("da???", 5, 10, subject));

		};
	}
}
