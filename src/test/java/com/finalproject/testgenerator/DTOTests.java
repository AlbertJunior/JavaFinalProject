package com.finalproject.testgenerator;

import com.finalproject.testgenerator.DTOs.QuestionDTO;
import com.finalproject.testgenerator.models.Question;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DTOTests {
    public class PostDtoUnitTest {

        private ModelMapper modelMapper = new ModelMapper();

        @Test
        public void whenConvertPostEntityToPostDto_thenCorrect() {
            Question question = new Question();
            question.setText("Intrebare??");
            question.setDifficulty(10);
            question.setTimeInSeconds(100);

            QuestionDTO questionDTO = modelMapper.map(question, QuestionDTO.class);
            assertEquals(question.getText(), questionDTO.getText());
            assertEquals(question.getDifficulty(), questionDTO.getDifficulty());
            assertEquals(question.getTimeInSeconds(), questionDTO.getTimeInSeconds());
        }

//        @Test
//        public void whenConvertPostDtoToPostEntity_thenCorrect() {
//            PostDto postDto = new PostDto();
//            postDto.setId(1L);
//            postDto.setTitle(randomAlphabetic(6));
//            postDto.setUrl("www.test.com");
//
//            Post post = modelMapper.map(postDto, Post.class);
//            assertEquals(postDto.getId(), post.getId());
//            assertEquals(postDto.getTitle(), post.getTitle());
//            assertEquals(postDto.getUrl(), post.getUrl());
//        }
    }
}
