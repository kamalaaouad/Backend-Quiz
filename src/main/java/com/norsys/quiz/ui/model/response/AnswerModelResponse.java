package com.norsys.quiz.ui.model.response;

public class AnswerModelResponse {

	
	private long Id;
    private String answerContent;
    private boolean correct;
	public long getId() {
		return Id;
	}
	public void setId(long id) {
		Id = id;
	}
	public String getAnswerContent() {
		return answerContent;
	}
	public void setAnswerContent(String answerContent) {
		this.answerContent = answerContent;
	}
	public boolean isCorrect() {
		return correct;
	}
	public void setCorrect(boolean correct) {
		this.correct = correct;
	}
	@Override
	public String toString() {
		return "AnswerModelResponse [Id=" + Id + ", answerContent=" + answerContent + ", correct=" + correct + "]";
	}
}
