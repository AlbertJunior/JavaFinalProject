package com.finalproject.testgenerator;

import com.finalproject.testgenerator.repositories.QuestionsRepository;
import com.finalproject.testgenerator.repositories.SubjectsRepository;
import com.finalproject.testgenerator.services.QuestionsService;
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

import java.io.IOException;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class TestGeneratorApplicationTests {

	@Autowired
	private SubjectsService subjectsService;
	@Autowired
	private QuestionsService questionsService;
	@Autowired
	private TestsService testsService;

	@Autowired
	private SubjectsRepository subjectsRepository;

	@Autowired
	private QuestionsRepository questionsRepository;

	@Test
	void contextLoads() {
	}

	@Test
	void getTest() throws IOException {
		HttpUriRequest request = new HttpGet( "http://localhost:8090/api/v1/tests?totalTime=10&subjectId=3");
		HttpResponse httpResponse =HttpClientBuilder.create().build().execute( request );
		assertThat(
				httpResponse.getStatusLine().getStatusCode()).
				isEqualTo(HttpStatus.SC_OK);
	}
	@Test
	void generateOptimTest(){

	}

	@Test
	void generateTest(){
		com.finalproject.testgenerator.models.Test test1 = testsService.createTest(10, 3);
		assertThat(test1.getTotalTime())
				.isEqualTo(10);
	}

	@Test
	void createSubject(){
		assertThat(subjectsService.getSubjectById(3).getName()).isEqualTo("matematica");
	}

}
