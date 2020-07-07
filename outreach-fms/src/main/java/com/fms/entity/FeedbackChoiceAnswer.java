package com.fms.entity;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_feedback_choice_answer")
public class FeedbackChoiceAnswer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "answer")
	private String answer;

	@JoinColumn(name = "question")
	@OneToOne(cascade = CascadeType.ALL)
	private FeedbackQuestion question;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	public FeedbackQuestion getQuestion() {
		return question;
	}

	public void setQuestion(FeedbackQuestion question) {
		this.question = question;
	}

}
