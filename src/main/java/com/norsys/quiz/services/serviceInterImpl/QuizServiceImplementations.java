package com.norsys.quiz.services.serviceInterImpl;


import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Service;


import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.repositories.QuizRepository;
import com.norsys.quiz.services.servicesInterfaces.QuestionService;
import com.norsys.quiz.services.servicesInterfaces.QuizService;
import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.shared.dto.QuizDto;
import com.norsys.quiz.ui.model.response.AnswerModelResponse;

@Service
public class QuizServiceImplementations implements QuizService {

	@Autowired
	private QuizRepository quizRepository;
	@Autowired
	private QuestionService questionService;
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
		for(QuizDto quiz:returnList) {
		 System.out.println(quiz.getId()+" "+quiz.getTitle());	
		}
		return returnList;
	}
	

	@Override
	public QuizDto getQuizById(long id) {
		Quiz  quizEntity =quizRepository.findById(id).orElseThrow();
		QuizDto quizDto = modelMapper.map(quizEntity, QuizDto.class);
		return quizDto;

	}

	@Override
	public String deleteQuizById(long id) {
		quizRepository.deleteById(id);
		return "Operation Successful";
	}

	@Override
	public QuizDto updateQuiz(long id,QuizDto quizDto) {
		System.out.println(id);
		System.out.println(quizDto.getTitle());
		Optional<Quiz> quizEntity = quizRepository.findById(id);
		if(quizEntity.isPresent()) {
			Quiz _quiz=quizEntity.get();
			QuizDto quizDtoo = modelMapper.map(_quiz, QuizDto.class);
			quizDtoo.setTitle(quizDto.getTitle());
		return modelMapper.map(quizRepository.save(modelMapper.map(quizDtoo, Quiz.class)),QuizDto.class);
		}else {
			throw new RuntimeException("Quiz isn't update");
		}
	}

	@Override
	public String calculateScore(long id, List<QuestionDto> question) {
		float score= 0;
		Optional<Quiz> quizEntity=quizRepository.findById(id);
		   if(quizEntity.isPresent()) {
			   Quiz _quiz=quizEntity.get();
			   QuizDto quizDto=modelMapper.map(_quiz, QuizDto.class);
			   System.out.println(quizDto.getQuestions().isEmpty());
		   }
		   for(QuestionDto qtd:question) {
			   if(qtd.getId() !=0) {
				   System.out.println(id);
					System.out.println(qtd.getQuestionContent()+" "+qtd.getId()+" "+ qtd.getAnswers());
					score+=questionService.calculeScore(qtd.getId(), qtd.getAnswers());
					System.out.println(score);
					
			   }else {
				   System.out.println("le dernier est null");
			   }
			   
		   }
		   
		
		return Float.toString(score);
	}
	
}
