package com.fms.dto;

public class DashboardDto {

	private int totalEventsCount;
	private int livesImpactedCount;
	private int totalVolunteersCount;
	private int TotalParticipantCount;

	public int getTotalEventsCount() {
		return totalEventsCount;
	}

	public void setTotalEventsCount(int totalEventsCount) {
		this.totalEventsCount = totalEventsCount;
	}

	public int getLivesImpactedCount() {
		return livesImpactedCount;
	}

	public void setLivesImpactedCount(int livesImpactedCount) {
		this.livesImpactedCount = livesImpactedCount;
	}

	public int getTotalVolunteersCount() {
		return totalVolunteersCount;
	}

	public void setTotalVolunteersCount(int totalVolunteersCount) {
		this.totalVolunteersCount = totalVolunteersCount;
	}

	public int getTotalParticipantCount() {
		return TotalParticipantCount;
	}

	public void setTotalParticipantCount(int totalParticipantCount) {
		TotalParticipantCount = totalParticipantCount;
	}

}
