package com.fms.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fms.dto.Response;
import com.fms.dto.UserDto;
import com.fms.entity.EventSummary;
import com.fms.service.UserService;

@RestController
@CrossOrigin
public class EventController {
	
	@Autowired
	UserService userService;
	
	@RequestMapping(value = "getEvent", method = RequestMethod.GET)
	public Page<EventSummary> getEventSummaryListPagination(@RequestParam(defaultValue = "0") int page, HttpServletRequest request){
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		return userService.getEventSummaryList(page, userDto);
		
	}
	
	@RequestMapping(value = "getEventDetails", method = RequestMethod.GET)
	public EventSummary getEventDetails(@RequestParam(name = "eventId") String eventId, HttpServletRequest request){
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		return userService.getEventDetails(eventId, userDto);
		
	}
	
	@RequestMapping(value = "getEventFeedbackDetails", method = RequestMethod.GET)
	public Response getEventFeedbackDetails(@RequestParam(name = "eventId") String eventId, HttpServletRequest request){
		UserDto userDto = (UserDto) request.getSession().getAttribute("user");
		return userService.getEventFeedbackDetails(eventId, userDto);
		
	}

}
