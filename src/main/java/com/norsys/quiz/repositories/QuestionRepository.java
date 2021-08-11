package com.norsys.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.norsys.quiz.oi.entities.Question;

public interface QuestionRepository extends JpaRepository<Question, Long> {
	Question findQuestionByQuestionContent(String content);
}
