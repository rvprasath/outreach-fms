package com.fms.service.impl;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.CreationHelper;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import com.fms.entity.EventSummary;
import com.fms.entity.User;

public class ExcelGenerator {

	public static ByteArrayInputStream generateExcel(List<User> users) throws IOException {
		String[] COLUMNs = { "Id", "Name", "Address", "Age" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Customers");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (User user : users) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(user.getName());
				row.createCell(1).setCellValue(user.getEmail());
				row.createCell(2).setCellValue(user.getPassword());

				Cell ageCell = row.createCell(3);
				ageCell.setCellValue(user.getEmployeeId());
				ageCell.setCellStyle(ageCellStyle);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayOutputStream generateExcelEmailAttachment(List<User> users) throws IOException {
		String[] COLUMNs = { "Id", "Name", "Address", "Age" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Customers");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (User user : users) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(user.getName());
				row.createCell(1).setCellValue(user.getEmail());
				row.createCell(2).setCellValue(user.getPassword());

				Cell ageCell = row.createCell(3);
				ageCell.setCellValue(user.getEmployeeId());
				ageCell.setCellStyle(ageCellStyle);
			}

			workbook.write(out); // write excel data to a byte array
			out.close();

			// Now use your ByteArrayDataSource as
			return out;
		}
	}

	public static ByteArrayInputStream generateExcelForEvent(List<EventSummary> eventSummaries) throws IOException {
		String[] COLUMNs = { "Event Id", "Month", "Base Location", "Council Name", "Beneficiary Name", "Event Name",
				"Event Date", "Business Unit", "Status", "Venue Address", "Total Volunteers", "Total Volunteer Hours",
				"Total Travel Hours" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Event");
			sheet.autoSizeColumn(1);

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle numberStyle = workbook.createCellStyle();
			numberStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));

			int rowIdx = 1;
			for (EventSummary eventSummary : eventSummaries) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(eventSummary.getEventId());
				row.createCell(1).setCellValue(eventSummary.getMonth());
				row.createCell(2).setCellValue(eventSummary.getBaseLocation());
				row.createCell(3).setCellValue(eventSummary.getCouncilName());
				row.createCell(4).setCellValue(eventSummary.getBeneficiaryName());
				row.createCell(5).setCellValue(eventSummary.getEventName());
				Cell eventDate = row.createCell(6);
				eventDate.setCellValue(eventSummary.getEventDate());
				eventDate.setCellStyle(cellStyleDate);
				row.createCell(7).setCellValue("-");
				row.createCell(8).setCellValue(eventSummary.getStatus());
				row.createCell(9).setCellValue(eventSummary.getVenueAddress());

				Cell totalNoOfVolunteer = row.createCell(10);
				totalNoOfVolunteer.setCellValue(eventSummary.getTotalNoOfVolunteer());
				totalNoOfVolunteer.setCellStyle(numberStyle);

				Cell volunteerHours = row.createCell(11);
				volunteerHours.setCellValue(eventSummary.getVolunteerHours());
				volunteerHours.setCellStyle(numberStyle);

				Cell totalTravelHours = row.createCell(12);
				totalTravelHours.setCellValue(eventSummary.getTotalTravelHours());
				totalTravelHours.setCellStyle(numberStyle);
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}

	public static ByteArrayInputStream generateExcelForReport(List<EventSummary> eventSummaries) throws IOException {
		String[] COLUMNs = { "Event Id", "Month", "Base Location", "Council Name", "Beneficiary Name", "project",
				"Business Unit", "Rating", "Status", "Volunteers", "Volunteering Hours", "Overall Hours", "Total Hours",
				"Lives Impacted" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Event");
			sheet.autoSizeColumn(1);

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle numberStyle = workbook.createCellStyle();
			numberStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));

			int rowIdx = 1;
			for (EventSummary eventSummary : eventSummaries) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(eventSummary.getEventId());
				row.createCell(1).setCellValue(eventSummary.getMonth());
				row.createCell(2).setCellValue(eventSummary.getBaseLocation());
				row.createCell(3).setCellValue(eventSummary.getCouncilName());
				row.createCell(4).setCellValue(eventSummary.getBeneficiaryName());
				row.createCell(5).setCellValue(eventSummary.getProject());
				row.createCell(6).setCellValue("-");

				Cell rating = row.createCell(7);
				rating.setCellValue(
						Objects.nonNull(eventSummary.getOverallRating()) ? eventSummary.getOverallRating() : 0);
				rating.setCellStyle(numberStyle);

				row.createCell(8).setCellValue(eventSummary.getStatus());

				Cell totalNoOfVolunteer = row.createCell(9);
				totalNoOfVolunteer.setCellValue(eventSummary.getTotalNoOfVolunteer());
				totalNoOfVolunteer.setCellStyle(numberStyle);

				Cell volunteeringHours = row.createCell(10);
				volunteeringHours.setCellValue(eventSummary.getVolunteerHours());
				volunteeringHours.setCellStyle(numberStyle);

				Cell overallHours = row.createCell(11);
				overallHours.setCellValue(eventSummary.getOverallVolunteeringHours());
				overallHours.setCellStyle(numberStyle);

				Cell totalHours = row.createCell(12);
				totalHours.setCellValue(eventSummary.getTotalTravelHours());
				totalHours.setCellStyle(numberStyle);

				Cell livesImpacted = row.createCell(13);
				livesImpacted.setCellValue(eventSummary.getLivesImpacted());
				livesImpacted.setCellStyle(numberStyle);

			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
	
	public static ByteArrayOutputStream generateExcelForReportEmail(List<EventSummary> eventSummaries) throws IOException {
		String[] COLUMNs = { "Event Id", "Month", "Base Location", "Council Name", "Beneficiary Name", "project",
				"Business Unit", "Rating", "Status", "Volunteers", "Volunteering Hours", "Overall Hours", "Total Hours",
				"Lives Impacted" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Event");
			sheet.autoSizeColumn(1);

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle numberStyle = workbook.createCellStyle();
			numberStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			CellStyle cellStyleDate = workbook.createCellStyle();
			cellStyleDate.setDataFormat(createHelper.createDataFormat().getFormat("m/d/yy h:mm"));

			int rowIdx = 1;
			for (EventSummary eventSummary : eventSummaries) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(eventSummary.getEventId());
				row.createCell(1).setCellValue(eventSummary.getMonth());
				row.createCell(2).setCellValue(eventSummary.getBaseLocation());
				row.createCell(3).setCellValue(eventSummary.getCouncilName());
				row.createCell(4).setCellValue(eventSummary.getBeneficiaryName());
				row.createCell(5).setCellValue(eventSummary.getProject());
				row.createCell(6).setCellValue("-");

				Cell rating = row.createCell(7);
				rating.setCellValue(
						Objects.nonNull(eventSummary.getOverallRating()) ? eventSummary.getOverallRating() : 0);
				rating.setCellStyle(numberStyle);

				row.createCell(8).setCellValue(eventSummary.getStatus());

				Cell totalNoOfVolunteer = row.createCell(9);
				totalNoOfVolunteer.setCellValue(eventSummary.getTotalNoOfVolunteer());
				totalNoOfVolunteer.setCellStyle(numberStyle);

				Cell volunteeringHours = row.createCell(10);
				volunteeringHours.setCellValue(eventSummary.getVolunteerHours());
				volunteeringHours.setCellStyle(numberStyle);

				Cell overallHours = row.createCell(11);
				overallHours.setCellValue(eventSummary.getOverallVolunteeringHours());
				overallHours.setCellStyle(numberStyle);

				Cell totalHours = row.createCell(12);
				totalHours.setCellValue(eventSummary.getTotalTravelHours());
				totalHours.setCellStyle(numberStyle);

				Cell livesImpacted = row.createCell(13);
				livesImpacted.setCellValue(eventSummary.getLivesImpacted());
				livesImpacted.setCellStyle(numberStyle);

			}

			workbook.write(out); // write excel data to a byte array
			out.close();

			// Now use your ByteArrayDataSource as
			return out;
		}
	}
	
	public static ByteArrayInputStream generateExcelForPMO(List<User> users) throws IOException {
		String[] COLUMNs = { "Email", "First Name", "Last Name" };
		try (Workbook workbook = new HSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream();) {
			CreationHelper createHelper = workbook.getCreationHelper();

			Sheet sheet = workbook.createSheet("Customers");

			Font headerFont = workbook.createFont();
			headerFont.setBold(true);
			headerFont.setColor(IndexedColors.BLUE.getIndex());

			CellStyle headerCellStyle = workbook.createCellStyle();
			headerCellStyle.setFont(headerFont);

			// Row for Header
			Row headerRow = sheet.createRow(0);

			// Header
			for (int col = 0; col < COLUMNs.length; col++) {
				Cell cell = headerRow.createCell(col);
				cell.setCellValue(COLUMNs[col]);
				cell.setCellStyle(headerCellStyle);
			}

			// CellStyle for Age
			CellStyle ageCellStyle = workbook.createCellStyle();
			ageCellStyle.setDataFormat(createHelper.createDataFormat().getFormat("#"));

			int rowIdx = 1;
			for (User user : users) {
				Row row = sheet.createRow(rowIdx++);

				row.createCell(0).setCellValue(user.getName());
				row.createCell(1).setCellValue(user.getEmail());
				row.createCell(2).setCellValue("");
			}

			workbook.write(out);
			return new ByteArrayInputStream(out.toByteArray());
		}
	}
}