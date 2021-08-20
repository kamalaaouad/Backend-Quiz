package com.norsys.quiz.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.norsys.quiz.oi.entities.Question;

import com.norsys.quiz.services.servicesInterfaces.QuestionService;
import com.norsys.quiz.shared.dto.QuestionDto;
import com.norsys.quiz.ui.model.response.AnswerModelResponse;


@CrossOrigin("*")
@RestController
@RequestMapping("question")
public class QuestionController {
	
	@Autowired
	private QuestionService questionService;
	
	ModelMapper modelMapper = new ModelMapper();
	
	@PostMapping("create/{id}")
	public ResponseEntity createQuestion(@RequestBody Question queestion, @PathVariable long id) {
		try {
			QuestionDto questionDto = modelMapper.map(queestion, QuestionDto.class);
			return new ResponseEntity<>(questionService.createQuestion(questionDto,id),HttpStatus.CREATED);
		} catch (Exception e) {
			e.getStackTrace();
			return new ResponseEntity<>(null,HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	
	@GetMapping("/getAllQuestion")
	public List<QuestionDto> getAllQuestion() {
		return questionService.getAllQuestions();
		
	}
	@GetMapping("/getQuestionById/{id}")
	public QuestionDto getQuestionById(@PathVariable("id") long id) {
		return questionService.getQuestionById(id);
	}
	
	@DeleteMapping(path = "/deleteQuestionById/{id}")
	public String deleteQuestionById(@PathVariable("id") long id) {
		
		return questionService.deleteQuestionById(id);
	}
	
	@PutMapping("updateQuestion/{id}")
	public ResponseEntity updateQuestion(@PathVariable("id") long id,@RequestBody QuestionDto question) {
		System.out.println(id);
		System.out.println(question);
		try {
			return new ResponseEntity(questionService.updateQuestion(id, question),HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
	@PostMapping("CalculateScore/{id}")
	public ResponseEntity calculateScore(@PathVariable("id")long id,@RequestBody List<AnswerModelResponse> answers) {
		System.out.println(answers);
		try {
			return new ResponseEntity(questionService.calculeScore(id, answers),HttpStatus.OK);
		} catch (Exception e) {
			e.getMessage();
			return new ResponseEntity(HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

}
