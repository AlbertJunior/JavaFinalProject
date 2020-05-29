package com.finalproject.testgenerator;

import com.finalproject.testgenerator.DTOs.AnswerDTO;
import com.finalproject.testgenerator.DTOs.QuestionDTO;
import com.finalproject.testgenerator.models.Answer;
import com.finalproject.testgenerator.models.Question;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class DTOTests {

    private ModelMapper modelMapper = new ModelMapper();

    @Test
    void whenConvertPostEntityToPostDtoQuestion_thenCorrect() {
        Question question = new Question();
        question.setText("Question??");
        question.setDifficulty(10);
        question.setTimeInSeconds(100);

        QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
        assertEquals(question.getText(), questionDTO.getText());
        assertEquals(question.getDifficulty(), questionDTO.getDifficulty());
        assertEquals(question.getTimeInSeconds(), questionDTO.getTimeInSeconds());
    }

    @Test
    public void whenConvertPostDtoToPostEntityQuestion_thenCorrect() {
        QuestionDTO postDto = new QuestionDTO();
        postDto.setDifficulty(10);
        postDto.setText("Question?");
        postDto.setTimeInSeconds(100);
        Question post = modelMapper.map(postDto, Question.class);
        assertEquals(postDto.getDifficulty(), post.getDifficulty());
        assertEquals(postDto.getText(), post.getText());
        assertEquals(postDto.getTimeInSeconds(), post.getTimeInSeconds());
    }

    @Test
    public void whenConvertPostEntityToPostDtoAnswer_thenCorrect() {
        Answer answer = new Answer();
        answer.setText("Answer");
        answer.setVerdict(1);

        AnswerDTO answerDTO = modelMapper.map(answer, AnswerDTO.class);
        assertEquals(answer.getText(), answerDTO.getText());
        assertEquals(answer.getVerdict(), answerDTO.getVerdict());
    }

    @Test
    public void whenConvertPostDtoToPostEntityAnswer_thenCorrect() {
        AnswerDTO postDto = new AnswerDTO();
        postDto.setVerdict(0);
        postDto.setText("Question?");
        Answer post = modelMapper.map(postDto, Answer.class);
        assertEquals(postDto.getVerdict(), post.getVerdict());
        assertEquals(postDto.getText(), post.getText());
    }
}
