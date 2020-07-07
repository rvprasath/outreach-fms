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
@Table(name = "t_participant_feedback_status")
public class ParticipantFeedbackStatus implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "event_id")
	private String eventId;

	@Column(name = "employee_id")
	private int employeeId;

	@Column(name = "employee_name")
	private String employeeName;

	@Column(name = "employee_email")
	private String employeeEmail;

	@Column(name = "event_status_code")
	private int eventStatusCode;

	@Column(name = "event_status")
	private String eventStatus;

	@Column(name = "is_feedback_sent")
	private int isFeedbackSent;

	@Column(name = "is_feedback_completed")
	private int isFeedbackCompleted;

	@Temporal(value = TemporalType.TIME)
	@Column(name = "feedback_date")
	private Date feedbackDate;

	@Column(name = "random_value")
	private String randomValue;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(int employeeId) {
		this.employeeId = employeeId;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public String getEmployeeEmail() {
		return employeeEmail;
	}

	public void setEmployeeEmail(String employeeEmail) {
		this.employeeEmail = employeeEmail;
	}

	public int getEventStatusCode() {
		return eventStatusCode;
	}

	public void setEventStatusCode(int eventStatusCode) {
		this.eventStatusCode = eventStatusCode;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public int getIsFeedbackSent() {
		return isFeedbackSent;
	}

	public void setIsFeedbackSent(int isFeedbackSent) {
		this.isFeedbackSent = isFeedbackSent;
	}

	public int getIsFeedbackCompleted() {
		return isFeedbackCompleted;
	}

	public void setIsFeedbackCompleted(int isFeedbackCompleted) {
		this.isFeedbackCompleted = isFeedbackCompleted;
	}

	public Date getFeedbackDate() {
		return feedbackDate;
	}

	public void setFeedbackDate(Date feedbackDate) {
		this.feedbackDate = feedbackDate;
	}

	public String getRandomValue() {
		return randomValue;
	}

	public void setRandomValue(String randomValue) {
		this.randomValue = randomValue;
	}

}
