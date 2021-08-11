package com.norsys.quiz.services.serviceInterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.norsys.quiz.oi.entities.Answer;
import com.norsys.quiz.oi.entities.Question;
import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.repositories.QuestionRepository;
import com.norsys.quiz.services.servicesInterfaces.QuestionService;
import com.norsys.quiz.services.servicesInterfaces.QuizService;
import com.norsys.quiz.shared.dto.AnswerDto;
import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.shared.dto.QuizDto;
import com.norsys.quiz.ui.model.response.QuizModelResponse;



@Service
public class QuestionServiceImplementation implements QuestionService {
	@Autowired
	private QuestionRepository questionsRespository;
	
	private ModelMapper modelMapper = new ModelMapper();
	
	public QuestionServiceImplementation(QuestionRepository qR) {
		this.questionsRespository = qR;
	}
	
	@Autowired
	private QuizService quizService;
	
	@Override
	public QuestionDto createQuestion(QuestionDto questionDto,long id) {
		QuizDto quizz = this.quizService.getQuizById(id);
		QuizModelResponse quizModel = modelMapper.map(quizz, QuizModelResponse.class);
		questionDto.setQuiz(quizModel);
		
		if(questionsRespository.findQuestionByQuestionContent(questionDto.getQuestionContent())!=null)
			throw new RuntimeException("Question already exists in Database");
		
		
		
		Question questionEntity = modelMapper.map(questionDto, Question.class);
		for(Answer answer: questionEntity.getAnswers()){
            answer.setQuestion(questionEntity);
        }
		QuestionDto quDto =modelMapper.map(questionsRespository.save(questionEntity),QuestionDto.class );
		  System.out.println(quDto.getAnswers().toString());
		return quDto;
	}

	@Override
	public List<QuestionDto> getAllQuestions() {
		List<QuestionDto> returnList = new ArrayList<>();
		for(Question  question: questionsRespository.findAll()) {
			QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
			returnList.add(questionDto);
		}
		return returnList;
	}

	@Override
	public QuestionDto getQuestionById(long id) {
		Question questionEntity = questionsRespository.getById(id);
		QuestionDto  questionDto = modelMapper.map(questionEntity, QuestionDto.class);
		return questionDto;
	}

	@Override
	public String deleteQuestionById(long id) {
		questionsRespository.deleteById(id);
		return "Operation Successful";
	}

}
