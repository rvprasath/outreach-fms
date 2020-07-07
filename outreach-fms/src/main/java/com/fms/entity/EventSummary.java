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
@Table(name = "t_event_summary")
public class EventSummary implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "id")
	private int id;

	@Column(name = "poc_id")
	private int pocId;

	@Column(name = "poc_name")
	private String pocName;

	@Column(name = "event_id")
	private String eventId;

	@Column(name = "event_name")
	private String eventName;

	@Column(name = "month")
	private String month;

	@Column(name = "event_description")
	private String eventDescription;

	@Column(name = "base_location")
	private String baseLocation;

	@Column(name = "beneficiary_name")
	private String beneficiaryName;

	@Column(name = "venue_address")
	private String venueAddress;

	@Column(name = "council_name")
	private String councilName;

	@Column(name = "project")
	private String project;

	@Column(name = "category")
	private String category;

	@Temporal(value = TemporalType.DATE)
	@Column(name = "event_date")
	private Date eventDate;

	@Column(name = "total_no_of_volunteer")
	private int totalNoOfVolunteer;

	@Column(name = "volunteer_hours")
	private int volunteerHours;

	@Column(name = "total_travel_hours")
	private int totalTravelHours;

	@Column(name = "overall_volunteering_hours")
	private int overallVolunteeringHours;

	@Column(name = "lives_impacted")
	private int livesImpacted;

	@Column(name = "activity_type")
	private int activityType;

	@Column(name = "status")
	private String status;

	@Column(name = "poc_contact_no")
	private int pocContactNo;

	@Column(name = "overall_rating")
	private int overallRating;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPocId() {
		return pocId;
	}

	public void setPocId(int pocId) {
		this.pocId = pocId;
	}

	public String getPocName() {
		return pocName;
	}

	public void setPocName(String pocName) {
		this.pocName = pocName;
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public String getEventName() {
		return eventName;
	}

	public void setEventName(String eventName) {
		this.eventName = eventName;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public String getEventDescription() {
		return eventDescription;
	}

	public void setEventDescription(String eventDescription) {
		this.eventDescription = eventDescription;
	}

	public String getBaseLocation() {
		return baseLocation;
	}

	public void setBaseLocation(String baseLocation) {
		this.baseLocation = baseLocation;
	}

	public String getBeneficiaryName() {
		return beneficiaryName;
	}

	public void setBeneficiaryName(String beneficiaryName) {
		this.beneficiaryName = beneficiaryName;
	}

	public String getVenueAddress() {
		return venueAddress;
	}

	public void setVenueAddress(String venueAddress) {
		this.venueAddress = venueAddress;
	}

	public String getCouncilName() {
		return councilName;
	}

	public void setCouncilName(String councilName) {
		this.councilName = councilName;
	}

	public String getProject() {
		return project;
	}

	public void setProject(String project) {
		this.project = project;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Date getEventDate() {
		return eventDate;
	}

	public void setEventDate(Date eventDate) {
		this.eventDate = eventDate;
	}

	public int getTotalNoOfVolunteer() {
		return totalNoOfVolunteer;
	}

	public void setTotalNoOfVolunteer(int totalNoOfVolunteer) {
		this.totalNoOfVolunteer = totalNoOfVolunteer;
	}

	public int getVolunteerHours() {
		return volunteerHours;
	}

	public void setVolunteerHours(int volunteerHours) {
		this.volunteerHours = volunteerHours;
	}

	public int getTotalTravelHours() {
		return totalTravelHours;
	}

	public void setTotalTravelHours(int totalTravelHours) {
		this.totalTravelHours = totalTravelHours;
	}

	public int getOverallVolunteeringHours() {
		return overallVolunteeringHours;
	}

	public void setOverallVolunteeringHours(int overallVolunteeringHours) {
		this.overallVolunteeringHours = overallVolunteeringHours;
	}

	public int getLivesImpacted() {
		return livesImpacted;
	}

	public void setLivesImpacted(int livesImpacted) {
		this.livesImpacted = livesImpacted;
	}

	public int getActivityType() {
		return activityType;
	}

	public void setActivityType(int activityType) {
		this.activityType = activityType;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public int getPocContactNo() {
		return pocContactNo;
	}

	public void setPocContactNo(int pocContactNo) {
		this.pocContactNo = pocContactNo;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overallRating) {
		this.overallRating = overallRating;
	}

}
