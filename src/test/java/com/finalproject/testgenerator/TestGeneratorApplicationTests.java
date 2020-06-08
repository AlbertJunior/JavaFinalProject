package com.finalproject.testgenerator;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.DTOs.QuestionDTO;
import com.finalproject.testgenerator.controllers.AnswersController;
import com.finalproject.testgenerator.controllers.SubjectsController;
import com.finalproject.testgenerator.controllers.TestsController;
import com.finalproject.testgenerator.exceptions.NotFoundException;
import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.models.Subject;
import com.finalproject.testgenerator.services.SubjectsService;
import com.finalproject.testgenerator.services.TestsService;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestGeneratorApplicationTests {

	@Autowired
	private SubjectsService subjectsService;
	@Autowired
	private TestsService testsService;
	@Autowired
	private TestsController testsController;
	@Autowired
	private AnswersController answersController;
	@Autowired
	private SubjectsController subjectsController;


	/**
	 * Test api for generate test
	 * @throws IOException
	 */
	@Test
	void getTest() throws IOException {
		HttpUriRequest request = new HttpGet( "https://tests-generator.herokuapp.com/api/v1/tests?totalTime=10&subjectId=6");
		HttpResponse httpResponse =HttpClientBuilder.create().build().execute( request );
		assertThat(httpResponse.getStatusLine().getStatusCode()).
				isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	void getTestNotFoundException() throws IOException {
		HttpUriRequest request = new HttpGet( "https://tests-generator.herokuapp.com/api/v1/tests?totalTime=10&subjectId=5");
		HttpResponse httpResponse =HttpClientBuilder.create().build().execute( request );
		assertThat(httpResponse.getStatusLine().getStatusCode()).
				isEqualTo(HttpStatus.SC_NOT_FOUND);
	}
	@Test
	void generateOptimumTest() throws NotFoundException {
		com.finalproject.testgenerator.models.Test test1 = testsService.createTest(10, 6);
		assertThat(test1.getDifficulty()).isEqualTo(19);
	}

	@Test
	void generateTest() throws NotFoundException {
		com.finalproject.testgenerator.models.Test test1 = testsService.createTest(10, 6);
		assertThat(test1.getTotalTime())
				.isEqualTo(10);
	}

	@Test
	void testSubjectName(){
		assertThat(subjectsService.getSubjectById(4).getName()).isEqualTo("matematica");
	}

	@Test
	void getSubjects() throws IOException {
		HttpUriRequest request = new HttpGet( "https://tests-generator.herokuapp.com/api/v1/subjects");
		HttpResponse httpResponse =HttpClientBuilder.create().build().execute( request );
		assertThat(
				httpResponse.getStatusLine().getStatusCode()).
				isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	void getSubject() throws IOException {
		HttpUriRequest request = new HttpGet( "https://tests-generator.herokuapp.com/api/v1/subjects/4");
		HttpResponse httpResponse =HttpClientBuilder.create().build().execute( request );
		assertThat(
				httpResponse.getStatusLine().getStatusCode()).
				isEqualTo(HttpStatus.SC_OK);
	}

	@Test
	void getTestWithController() throws NotFoundException {
		com.finalproject.testgenerator.models.Test test = testsController.getQuestions(10, 6);
		assertThat(test.getTotalTime()).isEqualTo(10);
	}

	@Test
	void getSubjectsWithController(){
		List<Subject> list = subjectsController.getSubjects();
		assertThat(list).isNotNull();
	}

	@Test
	void getSubjectWithController() throws NotFoundException {
		Subject subject = subjectsController.getSubject(4);
		assertThat(subject).isNotNull();
	}

	@Test
	void postSubjectWithController(){
		Subject subject1 = new Subject("romana");
		Subject subject2 = subjectsController.createSubject(subject1, null);
		assertThat(subject2).isNotNull();
	}

	@Test
	void deleteSubjectsWithController() throws NotFoundException {
		ResponseEntity<String> responseEntity =subjectsController.deleteSubject(5, null);
		assertThat(responseEntity).isNull();
	}


	@Test
	void getQuestionWithController() throws NotFoundException {
		List<QuestionDTO> list = subjectsController.getQuestions(4);
		assertThat(list).isNotNull();

		QuestionDTO questionDTO = subjectsController.getQuestion(6, 10);
		assertThat(questionDTO).isNotNull();

		QuestionDTO questionDTO1 = new QuestionDTO();
		questionDTO1.setTimeInSeconds(100);
		questionDTO1.setText("what??");
		questionDTO1.setDifficulty(10);

		List<QuestionDTO> list1 = new ArrayList<>();
		list1.add(questionDTO1);
		List<Question> list2 = subjectsController.createQuestion(4, list1, null);
		assertThat(list2).isNotNull();

		ResponseEntity<?> responseEntity =subjectsController.deleteQuestion(4, 6, null);
		assertThat(responseEntity).isNull();
	}

	@Test
	void getAnswersWithController() throws NotFoundException {
		List<AnswerDTO> list = answersController.getAnswers();
		assertThat(list).isNotNull();

		AnswerDTO answerDTO = answersController.getAnswer(2);
		assertThat(answerDTO).isNotNull();

		AnswerDTO answerDTO1 = new AnswerDTO();
		answerDTO1.setVerdict(1);
		answerDTO1.setText("YES");

		AnswerDTO answer = answersController.createAnswer(answerDTO1, null);
		assertThat(answer).isNotNull();

		ResponseEntity<?> responseEntity = answersController.deleteAnswer(2, null);
		assertThat(responseEntity).isNull();
	}

}
