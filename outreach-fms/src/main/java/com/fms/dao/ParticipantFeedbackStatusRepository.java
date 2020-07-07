package com.fms.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fms.entity.ParticipantFeedbackStatus;

@Repository
public interface ParticipantFeedbackStatusRepository extends JpaRepository<ParticipantFeedbackStatus, Integer> {

	List<ParticipantFeedbackStatus> findByEmployeeId(int employeeId);

	List<ParticipantFeedbackStatus> findByEventIdAndIsFeedbackSent(String eventId, int i);

	List<ParticipantFeedbackStatus> findByIsFeedbackSent(int i);

	ParticipantFeedbackStatus findByEventIdAndEmployeeIdAndRandomValue(String eventId, int employeeId,
			String secretCode);

	Object findByEventIdAndEmployeeIdAndIsFeedbackCompletedAndRandomValue(String eventId, int parseInt, int i,
			String secretCode);

}
