package com.finalproject.testgenerator;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
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
	ApplicationRunner applicationRunner(QuestionsRepository repository){
		return args -> {
			repository.save(new Question("ce faci?"));
			repository.save(new Question("tu?"));
			repository.save(new Question("da???"));

		};
	}
}
