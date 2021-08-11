package com.norsys.quiz.services.servicesInterfaces;

import java.util.List;

import com.norsys.quiz.shared.dto.QuestionDto;




public interface QuestionService {
	QuestionDto createQuestion(QuestionDto questionDto,long id);
    List<QuestionDto> getAllQuestions();
    QuestionDto getQuestionById(long id);
    String deleteQuestionById(long id);
}
