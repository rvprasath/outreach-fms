package com.fms.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_feedback_question")
public class FeedbackQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "question")
	private String question;

	@Column(name = "question_type")
	private String questionType;

	@Column(name = "participant_type")
	private String participantType;

	@OneToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "rating_question")
	private FeedbackRatingQuestion feedbackRatingQuestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getQuestionType() {
		return questionType;
	}

	public void setQuestionType(String questionType) {
		this.questionType = questionType;
	}

	public String getParticipantType() {
		return participantType;
	}

	public void setParticipantType(String participantType) {
		this.participantType = participantType;
	}

	public FeedbackRatingQuestion getFeedbackRatingQuestion() {
		return feedbackRatingQuestion;
	}

	public void setFeedbackRatingQuestion(FeedbackRatingQuestion feedbackRatingQuestion) {
		this.feedbackRatingQuestion = feedbackRatingQuestion;
	}

}
