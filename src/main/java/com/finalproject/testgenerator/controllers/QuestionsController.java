package com.finalproject.testgenerator.controllers;

import com.finalproject.testgenerator.models.Question;
import com.finalproject.testgenerator.services.QuestionsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/questions")
public class QuestionsController {

    private final QuestionsService service;

    @Autowired
    public QuestionsController (QuestionsService service){
        this.service = service;
    }

    @GetMapping("/hello")
    String hello(){
        return "Hello, World!";
    }

    @GetMapping
    public ResponseEntity<List<Question>> getQuestions(){
        List<Question> questions = service.getAllQuestions();
        return new ResponseEntity<>(questions, new HttpHeaders(), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<Question> createQuestion(@RequestBody Question question){
        Question question1 = service.createQuestion(question);
        return new ResponseEntity<>(question1, new HttpHeaders(), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateQuestion(@PathVariable("id") int id, @RequestBody Question question) {
        Question question1 =  service.getQuestionById(id);

        if (question1 == null) {
            return new ResponseEntity<>("Question not found", HttpStatus.NOT_FOUND);
        }
        if (question.getText() != null) {
            question1.setText(question.getText());
        }
        if (question.getSubject() != null) {
            question1.setSubject(question.getSubject());
        }
        if (question.getAnswers() != null) {
            question1.setAnswers(question.getAnswers());
        }
        if (question.getDifficulty() != -1) {
            question1.setDifficulty(question.getDifficulty());
        }
        if (question.getTimeInSeconds() != -1) {
            question1.setTimeInSeconds(question.getTimeInSeconds());
        }
        service.updateById(question1);

        return new ResponseEntity<>("Question updated", HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(value = "/{id}")
    public ResponseEntity<String> deleteSubject(@PathVariable int id) {
        Question question =  service.deleteById(id);

        if (question == null) {
            return new ResponseEntity<>("Subject not found", HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>("Subject removed", HttpStatus.OK);
    }

}
