package com.norsys.quiz.services.servicesInterfaces;

import java.util.List;

import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.ui.model.response.AnswerModelResponse;




public interface QuestionService {
	QuestionDto createQuestion(QuestionDto questionDto,long id);
    List<QuestionDto> getAllQuestions();
    QuestionDto getQuestionById(long id);
    String deleteQuestionById(long id);
	float calculeScore(long id, List<AnswerModelResponse> answer);
	QuestionDto updateQuestion(long id, QuestionDto question);
}
