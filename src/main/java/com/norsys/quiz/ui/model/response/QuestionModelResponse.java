package com.norsys.quiz.ui.model.response;

import java.util.Set;

public class QuestionModelResponse {
	
	private long Id;
    private String questionContent;
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
}
