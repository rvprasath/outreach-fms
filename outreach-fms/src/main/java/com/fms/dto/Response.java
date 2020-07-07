package com.fms.dto;

import java.util.List;

import com.fms.entity.FeedbackChoiceAnswer;
import com.fms.entity.FeedbackRatingQuestion;
import com.fms.entity.UserFeedbackAnswer;

public class Response {
	private String status;
	private String message;
	private List<FeedbackChoiceAnswer> feedbackChoiceAnswers;
	private FeedbackRatingQuestion feedbackRatingQuestion;
	private String eventName;
	private List<UserFeedbackAnswer> userFeedbackAnswers;
	private int averageRating;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public FeedbackRatingQuestion getFeedbackRatingQuestion() {
		return feedbackRatingQuestion;
	}

	public void setFeedbackRatingQuestion(FeedbackRatingQuestion feedbackRatingQuestion) {
		this.feedbackRatingQuestion = feedbackRatingQuestion;
	}

	public List<FeedbackChoiceAnswer> getFeedbackChoiceAnswers() {
		return feedbackChoiceAnswers;
	}

	public void setFeedbackChoiceAnswers(List<FeedbackChoiceAnswer> feedbackChoiceAnswers) {
		this.feedbackChoiceAnswers = feedbackChoiceAnswers;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public List<UserFeedbackAnswer> getUserFeedbackAnswers() {
		return userFeedbackAnswers;
	}

	public void setUserFeedbackAnswers(List<UserFeedbackAnswer> userFeedbackAnswers) {
		this.userFeedbackAnswers = userFeedbackAnswers;
	}

	public int getAverageRating() {
		return averageRating;
	}

	public void setAverageRating(int averageRating) {
		this.averageRating = averageRating;
	}

}
