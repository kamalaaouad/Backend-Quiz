package com.norsys.quiz.services.servicesInterfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.http.ResponseEntity;

import com.norsys.quiz.oi.entities.Quiz;
//import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.shared.dto.QuizDto;

public interface QuizService {

	QuizDto createQuiz(QuizDto quizDto);
	List<QuizDto> getAllQuiz();
	QuizDto getQuizById(long id);
//	ResponseEntity getQuizById(long id);
	String deleteQuizById(long id);
}
