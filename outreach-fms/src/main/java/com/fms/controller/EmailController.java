package com.fms.controller;

import java.io.IOException;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Response;
import com.fms.service.UserService;

@RestController
@CrossOrigin
public class EmailController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/sendEmailForFeedback")
	public Response sendEmailForFeedback(@RequestParam(value = "eventId") String eventId)
			throws AddressException, MessagingException, IOException {
		return userService.sendEmailsForFeedback(eventId);
	}

}
