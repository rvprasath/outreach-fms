package com.fms.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "t_feedback_rating_question")
public class FeedbackRatingQuestion implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "rating_question")
	private String ratingQuestion;

	@Column(name = "like_question")
	private String likeQuestion;

	@Column(name = "dislike_question")
	private String dislikeQuestion;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getRatingQuestion() {
		return ratingQuestion;
	}

	public void setRatingQuestion(String ratingQuestion) {
		this.ratingQuestion = ratingQuestion;
	}

	public String getLikeQuestion() {
		return likeQuestion;
	}

	public void setLikeQuestion(String likeQuestion) {
		this.likeQuestion = likeQuestion;
	}

	public String getDislikeQuestion() {
		return dislikeQuestion;
	}

	public void setDislikeQuestion(String dislikeQuestion) {
		this.dislikeQuestion = dislikeQuestion;
	}

}
