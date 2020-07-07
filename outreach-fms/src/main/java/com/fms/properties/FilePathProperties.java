package com.fms.properties;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "shared.file")
public class FilePathProperties {

	private String path;
	private String newPathName;
	private String newPath;
	private String excelVolunteerNotAttended;
	private String excelVolunteerUnregistered;
	private String excelVolunteerEventInformation;
	private String excelVolunteerEventSummary;

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getNewPathName() {
		return newPathName;
	}

	public void setNewPathName(String newPathName) {
		this.newPathName = newPathName;
	}

	public String getNewPath() {
		return newPath;
	}

	public void setNewPath(String newPath) {
		this.newPath = newPath;
	}

	public String getExcelVolunteerNotAttended() {
		return excelVolunteerNotAttended;
	}

	public void setExcelVolunteerNotAttended(String excelVolunteerNotAttended) {
		this.excelVolunteerNotAttended = excelVolunteerNotAttended;
	}

	public String getExcelVolunteerUnregistered() {
		return excelVolunteerUnregistered;
	}

	public void setExcelVolunteerUnregistered(String excelVolunteerUnregistered) {
		this.excelVolunteerUnregistered = excelVolunteerUnregistered;
	}

	public String getExcelVolunteerEventInformation() {
		return excelVolunteerEventInformation;
	}

	public void setExcelVolunteerEventInformation(String excelVolunteerEventInformation) {
		this.excelVolunteerEventInformation = excelVolunteerEventInformation;
	}

	public String getExcelVolunteerEventSummary() {
		return excelVolunteerEventSummary;
	}

	public void setExcelVolunteerEventSummary(String excelVolunteerEventSummary) {
		this.excelVolunteerEventSummary = excelVolunteerEventSummary;
	}
	
	

}
