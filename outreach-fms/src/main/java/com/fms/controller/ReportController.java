package com.fms.controller;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Response;
import com.fms.dto.UserDto;
import com.fms.entity.EventSummary;
import com.fms.service.UserService;
import com.fms.service.impl.ExcelGenerator;

@RestController
@CrossOrigin
public class ReportController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "getEventReports", method = RequestMethod.GET)
	public Page<EventSummary> getEventReports(@RequestParam(defaultValue = "0") int page, HttpServletRequest request) {
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		return userService.getEventSummaryListForReports(page, userDto);

	}

	@GetMapping(value = "/downloadReportExcel", produces = "application/ms-excel; charset=utf-8")
	public ResponseEntity<InputStreamResource> downloadReportExcel(@RequestParam(name = "filename") String filename,
			HttpServletRequest request) throws IOException {

		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		List<EventSummary> eventSummaries = userService.getEventList(userDto);

		ByteArrayInputStream in = ExcelGenerator.generateExcelForReport(eventSummaries);

		HttpHeaders headers = new HttpHeaders();
		headers.add("Content-Disposition", "attachment; filename=customers.xlsx");
		headers.add("filename", filename + ".xls");

		return ResponseEntity.ok().headers(headers).body(new InputStreamResource(in));
	}

	@GetMapping(value = "/sendReportExcelInEmail")
	public Response sendReportExcelInEmail(@RequestParam(name = "email") String email, HttpServletRequest request)
			throws IOException, AddressException, MessagingException {
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		List<EventSummary> eventSummaries = userService.getEventList(userDto);
		ByteArrayOutputStream bos = ExcelGenerator.generateExcelForReportEmail(eventSummaries);
		return userService.sendEmailReportAttachment(email, bos);
	}

}
