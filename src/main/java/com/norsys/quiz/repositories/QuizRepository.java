package com.norsys.quiz.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import com.norsys.quiz.oi.entities.Quiz;

public interface QuizRepository extends JpaRepository<Quiz, Long> {
	Quiz findQuizByTitle(String title);
}
