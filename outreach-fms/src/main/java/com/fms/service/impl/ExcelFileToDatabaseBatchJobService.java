package com.fms.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.annotation.Resource;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fms.dao.ExcelFileToDatabaseBatchJobVolunteerEnrollmentNotAttendedRepository;
import com.fms.dao.ExcelFileToDatabaseBatchJobVolunteerEnrollmentUnregisterRepository;
import com.fms.dao.ExcelFileToDatabaseBatchJobVolunteerEventInformationRepository;
import com.fms.dao.ExcelFileToDatabaseBatchJobVolunteerEventSummaryRepository;
import com.fms.dao.ParticipantFeedbackStatusRepository;
import com.fms.dao.UserRepository;
import com.fms.entity.EventInformation;
import com.fms.entity.EventSummary;
import com.fms.entity.ParticipantFeedbackStatus;
import com.fms.entity.User;
import com.fms.entity.VolunteerEnrollmentNotAttended;
import com.fms.entity.VolunteerEnrollmentUnregister;
import com.fms.properties.FilePathProperties;

@Service
public class ExcelFileToDatabaseBatchJobService {

	@Autowired
	FilePathProperties filePathProperties;

	@Resource
	ExcelFileToDatabaseBatchJobVolunteerEnrollmentNotAttendedRepository volunteerNotAttendedDao;

	@Resource
	ExcelFileToDatabaseBatchJobVolunteerEnrollmentUnregisterRepository volunteerUnregisterDao;

	@Resource
	ExcelFileToDatabaseBatchJobVolunteerEventInformationRepository eventInformationDao;

	@Resource
	ExcelFileToDatabaseBatchJobVolunteerEventSummaryRepository eventSummaryDao;

	@Resource
	UserRepository userDao;

	@Resource
	ParticipantFeedbackStatusRepository participantFeedbackStatusDao;

	@Scheduled(cron = "*/10 * * * * *")
	public void scheduleExcelJobReadAndWrite() {
		System.out.println("File Path : " + filePathProperties.getPath());

		Set<String> files = null;
		try {
			files = getFilesFromDirIO(filePathProperties.getPath());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (String str : files) {
			System.out.println("File Name : " + str);
			readExcel(filePathProperties.getPath(), str);
		}
	}

	public Set<String> getFilesFromDirIO(String dir) throws IOException {
		Set<String> fileList = new HashSet<>();
		try (DirectoryStream<Path> stream = Files.newDirectoryStream(Paths.get(dir))) {
			for (Path path : stream) {
				if (!Files.isDirectory(path)) {
					fileList.add(path.getFileName().toString());
				}
			}
		}
		return fileList;
	}

	private void makeDirectory(File dir) {
		if (!dir.exists()) {// check existing
			dir.mkdir();// create directory
			dir.setReadable(true, false); // set readable
			dir.setWritable(true, false); // set writable
			dir.setExecutable(true, false); // set executable
		}
	}

	public void readExcel(String dir, String file) {
		try {

			FileInputStream excelFile = new FileInputStream(new File(dir + "/" + file));

			Workbook workbook = new XSSFWorkbook(excelFile);
			Sheet datatypeSheet = workbook.getSheetAt(0);

			if (filePathProperties.getExcelVolunteerNotAttended().equals(file)) {
				extractVolunteerNotAttendedExcelToEntity(datatypeSheet);
			} else if (filePathProperties.getExcelVolunteerUnregistered().equals(file)) {
				extractVolunteerUnregisterExcelToEntity(datatypeSheet);
			} else if (filePathProperties.getExcelVolunteerEventInformation().equals(file)) {
				extractVolunteerEventInformationExcelToEntity(datatypeSheet);
			} else if (filePathProperties.getExcelVolunteerEventSummary().equals(file)) {
				extractVolunteerEventSummaryExcelToEntity(datatypeSheet);
			}

			workbook.close();

			File filePath = new File(dir + "/" + file);

			makeDirectory(new File(filePathProperties.getNewPath()));

			Date date = new Date();
			DateFormat df = new SimpleDateFormat("dd-MM-yyyy HH-mm-ss");
			String datestr = df.format(date);
			if (filePath.renameTo(new File(filePathProperties.getNewPath() + "/" + (file.split("\\.")[0]) + "_"
					+ datestr + "." + (file.split("\\.")[1])))) {
				filePath.delete();
				System.out.println("File moved successfully");
			} else {
				System.out.println("Failed to move the file");
			}

		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public boolean extractVolunteerNotAttendedExcelToEntity(Sheet datatypeSheet) throws ParseException {
		java.util.Iterator<Row> iterator = datatypeSheet.iterator();
		List<VolunteerEnrollmentNotAttended> volunteerEnrollmentNotAttendeds = new ArrayList<VolunteerEnrollmentNotAttended>();
		List<ParticipantFeedbackStatus> feedbackStatus = new ArrayList<ParticipantFeedbackStatus>();
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int countRow = 0;
		while (iterator.hasNext()) {
			countRow++;
			Row currentRow = iterator.next();
			java.util.Iterator<Cell> cellIterator = currentRow.iterator();
			VolunteerEnrollmentNotAttended volunteerEnrollmentNotAttended = new VolunteerEnrollmentNotAttended();
			ParticipantFeedbackStatus feedback = new ParticipantFeedbackStatus();
			User user = new User();
			if (countRow > 1) {
				int countCol = 0;
				while (cellIterator.hasNext()) {
					countCol++;
					Cell currentCell = cellIterator.next();
					switch (countCol) {
					case 1:
						volunteerEnrollmentNotAttended.setEventId(currentCell.getStringCellValue());
						feedback.setEventId(currentCell.getStringCellValue());
						break;
					case 2:
						volunteerEnrollmentNotAttended.setEventName(currentCell.getStringCellValue());
						break;
					case 3:
						volunteerEnrollmentNotAttended.setBeneficiaryName(currentCell.getStringCellValue());
						break;
					case 4:
						volunteerEnrollmentNotAttended.setBaseLocation(currentCell.getStringCellValue());
						break;
					case 5:
						Date d = currentCell.getDateCellValue();
						volunteerEnrollmentNotAttended.setEventDate(sdf.parse(sdf.format(d)));
						break;
					case 6:
						volunteerEnrollmentNotAttended.setEmployeeId((int) currentCell.getNumericCellValue());
						feedback.setEventStatusCode(3);
						feedback.setEventStatus("NOT ATTENDED");
						feedback.setIsFeedbackSent(0);
						feedback.setIsFeedbackCompleted(0);
						feedback.setEmployeeId((int) currentCell.getNumericCellValue());
						user.setEmployeeId((int) currentCell.getNumericCellValue());
						user.setRole(4);
						break;
					default:
						break;
					}

				}
				volunteerEnrollmentNotAttendeds.add(volunteerEnrollmentNotAttended);
				users.add(user);
				feedbackStatus.add(feedback);
			}

		}
		saveParticipantInfomationToDB(users, feedbackStatus);
		return saveVolunteerNotAttendedToDB(volunteerEnrollmentNotAttendeds);
	}

	@Transactional
	public boolean saveVolunteerNotAttendedToDB(List<VolunteerEnrollmentNotAttended> volunteerEnrollmentNotAttendeds) {
		volunteerNotAttendedDao.saveAll(volunteerEnrollmentNotAttendeds);
		return true;
	}

	public boolean extractVolunteerUnregisterExcelToEntity(Sheet datatypeSheet) throws ParseException {
		java.util.Iterator<Row> iterator = datatypeSheet.iterator();
		List<VolunteerEnrollmentUnregister> volunteerEnrollmentUnregisters = new ArrayList<VolunteerEnrollmentUnregister>();
		List<ParticipantFeedbackStatus> feedbackStatus = new ArrayList<ParticipantFeedbackStatus>();
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int countRow = 0;
		while (iterator.hasNext()) {
			countRow++;
			Row currentRow = iterator.next();
			java.util.Iterator<Cell> cellIterator = currentRow.iterator();
			VolunteerEnrollmentUnregister volunteerEnrollmentUnregister = new VolunteerEnrollmentUnregister();
			ParticipantFeedbackStatus feedback = new ParticipantFeedbackStatus();
			User user = new User();
			if (countRow > 1) {
				int countCol = 0;
				while (cellIterator.hasNext()) {
					countCol++;
					Cell currentCell = cellIterator.next();
					switch (countCol) {
					case 1:
						volunteerEnrollmentUnregister.setEventId(currentCell.getStringCellValue());
						feedback.setEventId(currentCell.getStringCellValue());
						break;
					case 2:
						volunteerEnrollmentUnregister.setEventName(currentCell.getStringCellValue());
						break;
					case 3:
						volunteerEnrollmentUnregister.setBeneficiaryName(currentCell.getStringCellValue());
						break;
					case 4:
						volunteerEnrollmentUnregister.setBaseLocation(currentCell.getStringCellValue());
						break;
					case 5:
						Date d = currentCell.getDateCellValue();
						volunteerEnrollmentUnregister.setEventDate(sdf.parse(sdf.format(d)));
						break;
					case 6:
						volunteerEnrollmentUnregister.setEmployeeId((int) currentCell.getNumericCellValue());
						feedback.setEventStatusCode(2);
						feedback.setEventStatus("UNREGISTERED");
						feedback.setIsFeedbackSent(0);
						feedback.setIsFeedbackCompleted(0);
						feedback.setEmployeeId((int) currentCell.getNumericCellValue());
						user.setEmployeeId((int) currentCell.getNumericCellValue());
						user.setRole(4);
						break;
					default:
						break;
					}

				}
				volunteerEnrollmentUnregisters.add(volunteerEnrollmentUnregister);
				users.add(user);
				feedbackStatus.add(feedback);
			}

		}
		saveParticipantInfomationToDB(users, feedbackStatus);
		return saveVolunteerUnregisterToDB(volunteerEnrollmentUnregisters);
	}

	@Transactional
	public boolean saveVolunteerUnregisterToDB(List<VolunteerEnrollmentUnregister> volunteerEnrollmentUnregisters) {
		volunteerUnregisterDao.saveAll(volunteerEnrollmentUnregisters);
		return true;
	}

	public boolean extractVolunteerEventSummaryExcelToEntity(Sheet datatypeSheet) throws ParseException {
		java.util.Iterator<Row> iterator = datatypeSheet.iterator();
		List<EventSummary> eventSummarys = new ArrayList<EventSummary>();
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int countRow = 0;
		while (iterator.hasNext()) {
			countRow++;
			Row currentRow = iterator.next();
			java.util.Iterator<Cell> cellIterator = currentRow.iterator();
			EventSummary eventSummary = new EventSummary();
			User user = new User();
			user.setRole(3);
			if (countRow > 1) {
				int countCol = 0;
				while (cellIterator.hasNext()) {
					countCol++;
					Cell currentCell = cellIterator.next();
					switch (countCol) {
					case 1:
						eventSummary.setEventId(currentCell.getStringCellValue());
						break;
					case 2:
						eventSummary.setMonth(currentCell.getStringCellValue());
						break;
					case 3:
						eventSummary.setBaseLocation(currentCell.getStringCellValue());
						break;
					case 4:
						eventSummary.setBeneficiaryName(currentCell.getStringCellValue());
						break;
					case 5:
						eventSummary.setVenueAddress(currentCell.getStringCellValue());
						break;
					case 6:
						eventSummary.setCouncilName(currentCell.getStringCellValue());
						break;
					case 7:
						eventSummary.setProject(currentCell.getStringCellValue());
						break;
					case 8:
						eventSummary.setCategory(currentCell.getStringCellValue());
						break;
					case 9:
						eventSummary.setEventName(currentCell.getStringCellValue());
						break;
					case 10:
						eventSummary.setEventDescription(currentCell.getStringCellValue());
						break;
					case 11:
						Date d = currentCell.getDateCellValue();
						eventSummary.setEventDate(sdf.parse(sdf.format(d)));
						break;
					case 12:
						eventSummary.setTotalNoOfVolunteer((int) currentCell.getNumericCellValue());
						break;
					case 13:
						eventSummary.setVolunteerHours((int) currentCell.getNumericCellValue());
						break;
					case 14:
						eventSummary.setTotalTravelHours((int) currentCell.getNumericCellValue());
						break;
					case 15:
						eventSummary.setOverallVolunteeringHours((int) currentCell.getNumericCellValue());
						break;
					case 16:
						eventSummary.setLivesImpacted((int) currentCell.getNumericCellValue());
						break;
					case 17:
						eventSummary.setActivityType((int) currentCell.getNumericCellValue());
						break;
					case 18:
						eventSummary.setStatus(currentCell.getStringCellValue());
						break;
					case 19:
						user.setEmployeeId((int) currentCell.getNumericCellValue());
						eventSummary.setPocId((int) currentCell.getNumericCellValue());
						break;
					case 20:
						user.setName(currentCell.getStringCellValue());
						user.setRole(3);
						eventSummary.setPocName(currentCell.getStringCellValue());
						break;
					case 21:
						eventSummary.setPocContactNo((int) currentCell.getNumericCellValue());
						break;
					default:
						break;
					}

				}
				users.add(user);
				eventSummarys.add(eventSummary);
			}

		}
		savePocOfEventSummaryToDB(users);
		return saveVolunteerEventSummaryToDB(eventSummarys);
	}

	@Transactional
	public boolean savePocOfEventSummaryToDB(List<User> users) {
		userDao.saveAll(users);
		return true;
	}

	@Transactional
	public boolean saveVolunteerEventSummaryToDB(List<EventSummary> eventSummarys) {
		eventSummaryDao.saveAll(eventSummarys);
		return true;
	}

	public boolean extractVolunteerEventInformationExcelToEntity(Sheet datatypeSheet) throws ParseException {
		java.util.Iterator<Row> iterator = datatypeSheet.iterator();
		List<EventInformation> eventInformations = new ArrayList<EventInformation>();
		List<ParticipantFeedbackStatus> feedbackStatus = new ArrayList<ParticipantFeedbackStatus>();
		List<User> users = new ArrayList<User>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		int countRow = 0;
		while (iterator.hasNext()) {
			countRow++;
			Row currentRow = iterator.next();
			java.util.Iterator<Cell> cellIterator = currentRow.iterator();
			EventInformation eventInformation = new EventInformation();
			ParticipantFeedbackStatus feedback = new ParticipantFeedbackStatus();
			User user = new User();
			if (countRow > 1) {
				int countCol = 0;
				while (cellIterator.hasNext()) {
					countCol++;
					Cell currentCell = cellIterator.next();
					switch (countCol) {
					case 1:
						eventInformation.setEventId(currentCell.getStringCellValue());
						feedback.setEventId(currentCell.getStringCellValue());
						break;
					case 2:
						eventInformation.setBaseLocation(currentCell.getStringCellValue());
						break;
					case 3:
						eventInformation.setBeneficiaryName(currentCell.getStringCellValue());
						break;
					case 4:
						eventInformation.setCouncilName(currentCell.getStringCellValue());
						break;
					case 5:
						eventInformation.setEventName(currentCell.getStringCellValue());
						break;
					case 6:
						eventInformation.setEventDescription(currentCell.getStringCellValue());
						break;
					case 7:
						Date d = currentCell.getDateCellValue();
						eventInformation.setEventDate(sdf.parse(sdf.format(d)));
						break;
					case 8:
						eventInformation.setEmployeeId((int) currentCell.getNumericCellValue());
						feedback.setEmployeeId((int) currentCell.getNumericCellValue());
						user.setEmployeeId((int) currentCell.getNumericCellValue());
						break;
					case 9:
						eventInformation.setEmployeeName(currentCell.getStringCellValue());
						feedback.setEmployeeName(currentCell.getStringCellValue());
						feedback.setEventStatusCode(1);
						feedback.setEventStatus("PARTICIPATED");
						feedback.setIsFeedbackSent(0);
						feedback.setIsFeedbackCompleted(0);
						user.setName(currentCell.getStringCellValue());
						user.setRole(4);
						break;
					case 10:
						eventInformation.setVolunteerHours((int) currentCell.getNumericCellValue());
						break;
					case 11:
						eventInformation.setTravelHours((int) currentCell.getNumericCellValue());
						break;
					case 12:
						eventInformation.setLivesImpacted((int) currentCell.getNumericCellValue());
						break;
					case 13:
						eventInformation.setBusinessUnit(currentCell.getStringCellValue());
						break;
					case 14:
						eventInformation.setStatus(currentCell.getStringCellValue());
						break;
					case 15:
						eventInformation.setIiepCategory(currentCell.getStringCellValue());
						break;
					default:
						break;
					}

				}
				eventInformations.add(eventInformation);
				users.add(user);
				feedbackStatus.add(feedback);
			}

		}

		saveParticipantInfomationToDB(users, feedbackStatus);
		return saveVolunteerEventInformationToDB(eventInformations);
	}

	@Transactional
	public boolean saveParticipantInfomationToDB(List<User> users, List<ParticipantFeedbackStatus> feedbackStatus) {
		userDao.saveAll(users);
		participantFeedbackStatusDao.saveAll(feedbackStatus);
		return true;
	}

	@Transactional
	public boolean saveVolunteerEventInformationToDB(List<EventInformation> eventInformations) {
		eventInformationDao.saveAll(eventInformations);
		return true;
	}
}
