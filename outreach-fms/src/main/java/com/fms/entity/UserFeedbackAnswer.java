package com.fms.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "user_feedback_answer")
public class UserFeedbackAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "event_id")
	private String eventId;

	@Column(name = "feedback_type")
	private String feedbackType;

	@Column(name = "question_id")
	private int questionId;

	@Column(name = "choice_answer")
	private String choiceAnswer;

	@Column(name = "rating")
	private int rating;

	@Column(name = "like_answer")
	private String likeAnswer;

	@Column(name = "dislike_answer")
	private String dislikeAnswer;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "date")
	private Date date;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

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

	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public int getQuestionId() {
		return questionId;
	}

	public void setQuestionId(int questionId) {
		this.questionId = questionId;
	}

}
