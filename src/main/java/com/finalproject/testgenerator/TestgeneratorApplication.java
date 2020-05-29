package com.finalproject.testgenerator;

import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.models.User;
import com.finalproject.testgenerator.repositories.AnswersRepository;
import com.finalproject.testgenerator.repositories.QuestionsRepository;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import com.finalproject.testgenerator.repositories.UsersRepository;
import org.modelmapper.ModelMapper;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TestgeneratorApplication {

	public static void main(String[] args) {
		SpringApplication.run(TestgeneratorApplication.class, args);
	}

	@Bean
	public ModelMapper modelMapper() {
		return new ModelMapper();
	}

	@Bean
	ApplicationRunner applicationRunner(UsersRepository usersRepository, QuestionsRepository repository, SubjectsRepository subjectsRepository, AnswersRepository answersRepository){
		return args -> {
			Answer answer =  new Answer("VALEU", 1);
			answersRepository.save(answer);
			answersRepository.save(new Answer("raspuns1", 1));
			answersRepository.save(new Answer("raspuns2", 0));
			Subject subject = new Subject("matematica");
			subjectsRepository.save(subject);
			subject = new Subject("romana");
			subjectsRepository.save(subject);
			subject = new Subject("engleza");
			subjectsRepository.save(subject);

			repository.save(new Question("tu?", 3, 5, subject));
			repository.save(new Question("da???", 3, 5, subject));
			repository.save(new Question("ce faci?", 2, 4, subject));
			repository.save(new Question("tu?", 2, 4, subject));
			repository.save(new Question("da???", 5, 10, subject));

			User user = new User();
			user.setPassword("123");
			user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
			user.setUsername("Ramona");
			usersRepository.save(user);

		};
	}
}
