package com.norsys.quiz.shared.dto;

import java.util.Set;

import com.norsys.quiz.ui.model.response.AnswerModelResponse;
import com.norsys.quiz.ui.model.response.QuizModelResponse;


public class QuestionDto {

	private long Id;
    private String questionContent;
    private QuizModelResponse quiz;
    private Set<AnswerModelResponse> answers;
    
    
    public Set<AnswerModelResponse> getAnswers() {
		return answers;
	}
	public void setAnswers(Set<AnswerModelResponse> answers) {
		this.answers = answers;
	}
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getQuestionContent() {
		return questionContent;
	}
	public void setQuestionContent(String questionContent) {
		this.questionContent = questionContent;
	}
	public QuizModelResponse getQuiz() {
		return quiz;
	}
	public void setQuiz(QuizModelResponse quiz) {
		this.quiz = quiz;
	}
	
}
