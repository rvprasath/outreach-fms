package com.fms.controller;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fms.dto.DashboardDto;
import com.fms.dto.FeedbackAnswerResponse;
import com.fms.dto.Response;
import com.fms.dto.UserDto;
import com.fms.entity.EventSummary;
import com.fms.entity.FeedbackQuestion;
import com.fms.entity.User;
import com.fms.service.saveExcelToFileSystemService;
import com.fms.service.UserService;
import com.fms.service.impl.ExcelGenerator;

@RestController
@CrossOrigin
public class MainController {

	@Autowired
	UserService userService;

	@Autowired
	saveExcelToFileSystemService saveExcel;

	@RequestMapping(value = "/index")
	public String index() {
		return "pages/index.html";
	}

	@RequestMapping(value = "/dashboardDetails", method = RequestMethod.GET)
	public DashboardDto getDashbordDetails(HttpServletRequest request) {
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		return userService.getDashboardDetails(userDto);
	}

	@RequestMapping(value = "addPmo", method = RequestMethod.POST)
	public Response addPmo(@RequestParam(name = "email") String email, HttpServletRequest request) {
		return userService.addPmo(email);

	}

	@RequestMapping(value = "removePmo", method = RequestMethod.POST)
	public Response removePmo(@RequestParam(name = "email") String email, HttpServletRequest request) {
		return userService.removePmo(email);
	}

	@GetMapping(value = "getUsers")
	public Page<User> getEventReports(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		return userService.getUserDetailPagination(page);
	}

	@GetMapping(value = "getPocs")
	public Page<User> getPocs(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		return userService.getPocsListPagination(page);
	}

	@RequestMapping(value = "addPocEmail", method = RequestMethod.POST)
	public Response addPoc(@RequestParam(name = "id") String id, @RequestParam(name = "email") String email,
			HttpServletRequest request) {
		return userService.addPocEmail(Integer.valueOf(id), email);
	}

	@RequestMapping(value = "addRatingQuestion", method = RequestMethod.POST)
	public Response addRatingQuestion(@RequestParam(name = "ratingQues") String ratingQues,
			@RequestParam(name = "likeQues") String likeQues, @RequestParam(name = "dislikeQues") String dislikeQues,
			HttpServletRequest request) {
		return userService.addRatingQuestion(ratingQues, likeQues, dislikeQues);
	}

	@RequestMapping(value = "addUnregisterNotAttendedQuestion", method = RequestMethod.POST)
	public Response addUnregisterNotAttendedQuestion(@RequestParam(name = "question") String question,
			@RequestParam(name = "answers") String answers,
			@RequestParam(name = "participationType") String participationType, HttpServletRequest request) {
		String[] ans = answers.split("\\,");
		return userService.addUnregisterNotAttendedQuestion(question, ans, participationType);
	}

	@GetMapping(value = "getFeedbackQuestions")
	public Page<FeedbackQuestion> getFeedbackQuestions(@RequestParam(defaultValue = "0") int page,
			HttpServletRequest request) {
		return userService.getFeedbackQuestions(page);
	}

	@GetMapping(value = "/downloadEventExcel", produces = "application/ms-excel; charset=utf-8")
	public ResponseEntity<InputStreamResource> downloadEventExcel(@RequestParam(name = "filename") String filename,
			HttpServletRequest request) throws IOException {

		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		List<EventSummary> eventSummaries = userService.getEventList(userDto);

		ByteArrayInputStream in = ExcelGenerator.generateExcelForEvent(eventSummaries);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		headers.add("filename", filename + ".xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping(value = "/getPmoExcel", produces = "application/ms-excel; charset=utf-8")
	public ResponseEntity<InputStreamResource> getPmoExcel(@RequestParam(name = "filename") String filename)
			throws IOException {
		List<User> users = userService.getPmo();

		ByteArrayInputStream in = ExcelGenerator.generateExcelForPMO(users);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		headers.add("filename", filename + ".xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping(value = "getFeedbackForm")
	public Response feedbackForm(@RequestParam(name = "eventId") String eventId,
			@RequestParam(name = "employeeId") String employeeId, @RequestParam(name = "secretCode") String secretCode,
			HttpServletRequest request) {
		return userService.getFeedbackFormDetails(eventId, employeeId, secretCode);
	}

	@RequestMapping(value = "submitFeedback")
	public Response submitFeedback(@RequestBody FeedbackAnswerResponse feedbackAnswerResponse,
			HttpServletRequest request) {
		return userService.submitFeedback(feedbackAnswerResponse);
	}

	@PostMapping("/uploadExcelFile")
	public ResponseEntity<Response> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		Response response = new Response();
		try {
			saveExcel.save(file);
			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			response.setMessage(message);
			return ResponseEntity.status(HttpStatus.OK).body(response);
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			response.setMessage(message);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);

		}
	}
}
