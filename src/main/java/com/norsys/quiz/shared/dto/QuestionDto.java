package com.norsys.quiz.shared.dto;

import java.util.List;

import com.norsys.quiz.ui.model.response.AnswerModelResponse;
import com.norsys.quiz.ui.model.response.QuizModelResponse;


public class QuestionDto {

	
	private long Id;
    private String questionContent;
    private QuizModelResponse quiz;
    private List<AnswerModelResponse> answers;
    
    
    public List<AnswerModelResponse> getAnswers() {
		return answers;
	}
	public void setAnswers(List<AnswerModelResponse> answers) {
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
	@Override
	public String toString() {
		return "QuestionDto [Id=" + Id + ", questionContent=" + questionContent + ", answers="
				+ answers + "]";
	}
	
}
