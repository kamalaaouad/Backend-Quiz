package com.norsys.quiz.controllers;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.norsys.quiz.oi.entities.Quiz;
import com.norsys.quiz.repositories.QuizRepository;
import com.norsys.quiz.services.servicesInterfaces.QuizService;
import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.shared.dto.QuizDto;
import com.norsys.quiz.ui.model.response.QuizModelResponse;



@CrossOrigin(origins = "*")
@RestController
@RequestMapping("quiz") 
public class QuizController {
	@Autowired
	private QuizService quizService;
	@Autowired
	private QuizRepository qr;
	ModelMapper modelMapper = new ModelMapper();
	
	
	
	
	
	@PostMapping("create")
	public ResponseEntity<QuizDto> createQuiz(@RequestBody Quiz quiz){
		try {
			QuizDto quizDto = modelMapper.map(quiz, QuizDto.class);
			;
			return new ResponseEntity<QuizDto>(quizService.createQuiz(quizDto),HttpStatus.CREATED);
		} catch (Exception e) {
			System.out.println(e);
			e.getStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getQuizById/{id}")
	public QuizDto getTutorialById(@PathVariable("id") long id){
		return quizService.getQuizById(id);
	}
	
	@GetMapping("/getAll")
    public Set<QuizDto> getAllQuestions(){
        Set<QuizDto> returnValue = new HashSet<>();
        for(QuizDto questionDto: quizService.getAllQuiz()){
            returnValue.add(questionDto);
        }

        return returnValue;
    }
	
	@DeleteMapping(path = "/deleteQuiz/{id}")
	public String deleteQuizById(@PathVariable("id") long id) {
		return quizService.deleteQuizById(id);
	}
	
	@PostMapping("/CalculateScoreByQuiz/{id}")
	public ResponseEntity calculateScoreByQuiz(@PathVariable("id") long id,@RequestBody List<QuestionDto> questions) {
		try {
			return new ResponseEntity(quizService.calculateScore(id, questions),HttpStatus.OK);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@PutMapping("updateQuiz/{id}")
	public ResponseEntity updateQuiz(@PathVariable("id") long id,@RequestBody QuizDto quiz) {
		try {
			return new ResponseEntity(quizService.updateQuiz(id, quiz),HttpStatus.OK);
		} catch (Exception e) {
			e.getLocalizedMessage();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
