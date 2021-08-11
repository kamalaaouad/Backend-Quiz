package com.norsys.quiz.shared.dto;

import java.util.List;

import com.norsys.quiz.ui.model.response.QuestionModelResponse;



public class QuizDto {

	private long id;
	private String title;
	private List<QuestionModelResponse> questions;
	
	public List<QuestionModelResponse> getQuestions() {
		return questions;
	}
	public void setQuestions(List<QuestionModelResponse> questions) {
		this.questions = questions;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
}
