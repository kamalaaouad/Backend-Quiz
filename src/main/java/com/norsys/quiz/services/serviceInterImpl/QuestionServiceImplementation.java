package com.norsys.quiz.services.serviceInterImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.norsys.quiz.oi.entities.Answer;
import com.norsys.quiz.oi.entities.Question;
import com.norsys.quiz.repositories.QuestionRepository;
import com.norsys.quiz.services.servicesInterfaces.QuestionService;
import com.norsys.quiz.services.servicesInterfaces.QuizService;
import com.norsys.quiz.shared.dto.*;
import com.norsys.quiz.shared.dto.QuizDto;
import com.norsys.quiz.ui.model.response.AnswerModelResponse;
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
	public QuestionDto createQuestion(QuestionDto questionDto, long id) {
		QuizDto quizz = this.quizService.getQuizById(id);
		QuizModelResponse quizModel = modelMapper.map(quizz, QuizModelResponse.class);
		questionDto.setQuiz(quizModel);

		if (questionsRespository.findQuestionByQuestionContent(questionDto.getQuestionContent()) != null)
			throw new RuntimeException("Question already exists in Database");

		Question questionEntity = modelMapper.map(questionDto, Question.class);
		for (Answer answer : questionEntity.getAnswers()) {
			answer.setQuestion(questionEntity);
		}
		QuestionDto quDto = modelMapper.map(questionsRespository.save(questionEntity), QuestionDto.class);
		System.out.println(quDto.getAnswers().toString());
		return quDto;
	}

	@Override
	public List<QuestionDto> getAllQuestions() {
		List<QuestionDto> returnList = new ArrayList<>();
		for (Question question : questionsRespository.findAll()) {
			QuestionDto questionDto = modelMapper.map(question, QuestionDto.class);
			returnList.add(questionDto);
		}
		return returnList;
	}

	@Override
	public QuestionDto getQuestionById(long id) {
		Question questionEntity = questionsRespository.getById(id);
		QuestionDto questionDto = modelMapper.map(questionEntity, QuestionDto.class);
		return questionDto;
	}

	@Override
	public String deleteQuestionById(long id) {
		questionsRespository.deleteById(id);
		return "Operation Successful";
	}

	
	@Override
	public QuestionDto updateQuestion(long id, QuestionDto question) {
		System.out.println(id);
		Optional<Question> questionEntity = questionsRespository.findById(id);
		if (questionEntity.isPresent()) {
			Question _question = questionEntity.get();
			System.out.println("Yes come in");
			System.out.println(_question);
			_question.setQuestionContent(question.getQuestionContent());
			System.out.println(question.getAnswers());
			for (Answer answer : _question.getAnswers()) {
				for (Answer ans : modelMapper.map(question, Question.class).getAnswers()) {
					if (answer.getId() == ans.getId()) {
						answer.setAnswerContent(ans.getAnswerContent());
						answer.setCorrect(ans.isCorrect());
						answer.setQuestion(answer.getQuestion());
					}
				}
				System.out.println(answer.getQuestion().getId());
			}

			System.out.println(
					_question.getAnswers() + " " + modelMapper.map(_question, QuestionDto.class).getQuestionContent());

			return modelMapper.map(questionsRespository.save(_question), QuestionDto.class);
		} else {
			return null;
		}

	}

	
	@Override
	public float calculeScore(long id, List<AnswerModelResponse> answer) {
		float score = 0;
		int compteur=0;
		int compteur_=0;
		Optional<Question> questionEntity = questionsRespository.findById(id);
		if (questionEntity.isPresent()) {
			Question _question = questionEntity.get();
			for (AnswerModelResponse an : answer) {
				for (Answer anss : _question.getAnswers()) {
					if(an.getId()==anss.getId()) {
						 if((an.isCorrect()==anss.isCorrect())) {
							  if(an.isCorrect())
						          ++compteur;
							  else
								  System.out.println("la reponse est false");
						 }
						 else
							 System.out.println("not correct "+(--compteur));
					}
				}
			}
			for(Answer anss:_question.getAnswers()) {
				if(anss.isCorrect())
					System.out.println(++compteur_);
			}
		}
		System.out.println("compteur_ ="+compteur_+", compteur ="+compteur);
		if(compteur==compteur_) {
			score=1;
		}else {
			score=0;
			System.out.println("score est 0 = "+score);
		}
		return score;
	}

}
