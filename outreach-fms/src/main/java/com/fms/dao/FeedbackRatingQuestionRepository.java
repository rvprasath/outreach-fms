package com.fms.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.FeedbackRatingQuestion;

@Repository
public interface FeedbackRatingQuestionRepository extends JpaRepository<FeedbackRatingQuestion, Integer> {

}
