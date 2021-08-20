package com.norsys.quiz.services.servicesInterfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.shared.dto.QuestionDto;
//import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.shared.dto.QuizDto;

public interface QuizService {

	QuizDto createQuiz(QuizDto quizDto);
	List<QuizDto> getAllQuiz();
	QuizDto getQuizById(long id);
	String deleteQuizById(long id);
	String calculateScore(long id, List<QuestionDto> question);
	QuizDto updateQuiz(long id, QuizDto quizDto);
}
