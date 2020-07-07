package com.fms.service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.data.domain.Page;

import com.fms.dto.DashboardDto;
import com.fms.dto.FeedbackAnswerResponse;
import com.fms.dto.Response;
import com.fms.dto.UserDto;
import com.fms.entity.EventSummary;
import com.fms.entity.FeedbackQuestion;
import com.fms.entity.User;

public interface UserService {

	UserDto getUserDetails(String username, String password);

	DashboardDto getDashboardDetails(UserDto userDto);

	Page<EventSummary> getEventSummaryList(int page, UserDto userDto);

	EventSummary getEventDetails(String eventId, UserDto userDto);

	Page<EventSummary> getEventSummaryListForReports(int page, UserDto userDto);

	Response addPmo(String email);

	Page<User> getUserDetailPagination(int page);

	Response removePmo(String email);

	Page<User> getPocsListPagination(int page);

	Response addPocEmail(int id, String email);

	Response addRatingQuestion(String ratingQues, String likeQues, String dislikeQues);

	Response addUnregisterNotAttendedQuestion(String question, String[] ans, String participationType);

	Page<FeedbackQuestion> getFeedbackQuestions(int page);

	Response sendEmailsForFeedback(String eventId) throws AddressException, MessagingException, IOException;

//	List<EventSummary> getEventList();
	
	List<EventSummary> getEventList(UserDto userDto);

	Response sendEmailReportAttachment(String email, ByteArrayOutputStream bos) throws AddressException, MessagingException, IOException;

	List<User> getPmo();

	Response getFeedbackFormDetails(String eventId, String employeeId, String secretCode);

	Response submitFeedback(FeedbackAnswerResponse feedbackAnswerResponse);

	Response getEventFeedbackDetails(String eventId, UserDto userDto);

}
