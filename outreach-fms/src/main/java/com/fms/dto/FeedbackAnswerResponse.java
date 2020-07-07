package com.fms.dto;

public class FeedbackAnswerResponse {

	private int employeeId;
	private String eventId;
	private String feedbackType;
	private int questionId;
	private String choiceAnswer;
	private int rating;
	private String likeAnswer;
	private String dislikeAnswer;
	private String secretCode;

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getFeedbackType() {
		return feedbackType;
	}

	public void setFeedbackType(String feedbackType) {
		this.feedbackType = feedbackType;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

	public String getChoiceAnswer() {
		return choiceAnswer;
	}

	public void setChoiceAnswer(String choiceAnswer) {
		this.choiceAnswer = choiceAnswer;
	}

	public int getRating() {
		return rating;
	}

	public void setRating(int rating) {
		this.rating = rating;
	}

	public String getLikeAnswer() {
		return likeAnswer;
	}

	public void setLikeAnswer(String likeAnswer) {
		this.likeAnswer = likeAnswer;
	}

	public String getDislikeAnswer() {
		return dislikeAnswer;
	}

	public void setDislikeAnswer(String dislikeAnswer) {
		this.dislikeAnswer = dislikeAnswer;
	}

	public String getSecretCode() {
		return secretCode;
	}

	public void setSecretCode(String secretCode) {
		this.secretCode = secretCode;
	}

}
