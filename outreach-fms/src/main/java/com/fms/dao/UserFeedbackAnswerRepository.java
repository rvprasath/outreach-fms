package com.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.UserFeedbackAnswer;

@Repository
public interface UserFeedbackAnswerRepository extends JpaRepository<UserFeedbackAnswer, Integer> {

	List<UserFeedbackAnswer> findByEventId(String eventId);

}
