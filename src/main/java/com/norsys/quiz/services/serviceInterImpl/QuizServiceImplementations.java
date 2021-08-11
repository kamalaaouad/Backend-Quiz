package com.norsys.quiz.services.serviceInterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.norsys.quiz.oi.entities.Question;
import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.repositories.QuizRepository;
import com.norsys.quiz.services.servicesInterfaces.QuizService;
import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.shared.dto.QuizDto;

@Service
public class QuizServiceImplementations implements QuizService {

	@Autowired
	private QuizRepository quizRepository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public QuizServiceImplementations(QuizRepository quiz) {
		this.quizRepository = quiz;
	}

	@Override
	public QuizDto createQuiz(QuizDto quizDto) {
		if(quizRepository.findQuizByTitle(quizDto.getTitle())!=null)
			throw new RuntimeException("Quiz already exists in Database");
		Quiz quizEntity = modelMapper.map(quizDto, Quiz.class);
		return modelMapper.map(quizRepository.save(quizEntity),QuizDto.class );
	}

	@Override
	public List<QuizDto> getAllQuiz() {
		List<QuizDto> returnList = new ArrayList<>();
		for(Quiz quiz : quizRepository.findAll()) {
			returnList.add(modelMapper.map(quiz, QuizDto.class));
		}
		return returnList;
	}

	@Override
	public QuizDto getQuizById(long id) {
		Quiz  quizEntity =quizRepository.findById(id).orElseThrow();
		QuizDto quizDto = modelMapper.map(quizEntity, QuizDto.class);
		return quizDto;
//		return modelMapper.map(quizRepository.findById(id), QuizDto.class);

	}

	@Override
	public String deleteQuizById(long id) {
		quizRepository.deleteById(id);
		return "Operation Successful";
	}
	
	
}
