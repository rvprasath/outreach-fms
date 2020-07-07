package com.fms.service.impl;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import java.util.Random;

import javax.annotation.Resource;
import javax.mail.MessagingException;
import javax.mail.internet.AddressException;
import javax.transaction.Transactional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import com.fms.dao.EventInformationRepository;
import com.fms.dao.EventSummaryRepository;
import com.fms.dao.FeedbackChoiceAnswerRepository;
import com.fms.dao.FeedbackQuestionRepository;
import com.fms.dao.FeedbackRatingQuestionRepository;
import com.fms.dao.ParticipantFeedbackStatusRepository;
import com.fms.dao.UserFeedbackAnswerRepository;
import com.fms.dao.UserRepository;
import com.fms.dto.DashboardDto;
import com.fms.dto.FeedbackAnswerResponse;
import com.fms.dto.Response;
import com.fms.dto.UserDto;
import com.fms.entity.EventSummary;
import com.fms.entity.FeedbackChoiceAnswer;
import com.fms.entity.FeedbackQuestion;
import com.fms.entity.FeedbackRatingQuestion;
import com.fms.entity.ParticipantFeedbackStatus;
import com.fms.entity.User;
import com.fms.entity.UserFeedbackAnswer;
import com.fms.properties.EmailProperties;
import com.fms.properties.EmailTemplateProperties;
import com.fms.service.UserService;

@Service(value = "UserServiceImpl")
public class UserServiceImpl implements UserService {

	@Resource
	UserRepository userDao;

	@Resource
	EventInformationRepository eventInformationDao;

	@Resource
	EventSummaryRepository eventSummaryDao;

	@Resource
	FeedbackRatingQuestionRepository feedbackRatingQuestionDao;

	@Resource
	FeedbackQuestionRepository feedbackQuestionDao;

	@Resource
	FeedbackChoiceAnswerRepository feedbackChoiceAnswerDao;

	@Resource
	ParticipantFeedbackStatusRepository participantFeedbackStatusDao;

	@Autowired
	EmailService emailService;

	@Autowired
	EmailTemplateProperties emailTemplateProperties;

	@Autowired
	EmailProperties emailProperties;

	@Resource
	UserFeedbackAnswerRepository userFeedbackAnswerDao;

	@Transactional
	public UserDto getUserDetails(String userName, String password) {

		User user = userDao.findByEmailAndPassword(userName, password);
		if (Objects.nonNull(user)) {
			UserDto userDto = new UserDto();
			BeanUtils.copyProperties(user, userDto);
			return userDto;
		}

		return null;

	}

	@Override
	@Transactional
	public DashboardDto getDashboardDetails(UserDto userDto) {
		List<EventSummary> eventSummarys = null;
		DashboardDto dashboardDto = new DashboardDto();
		if (userDto.getRole() == 3) {
			eventSummarys = eventSummaryDao.findByPocId(userDto.getEmployeeId());
		} else {
			eventSummarys = eventSummaryDao.findAll();
		}
		dashboardDto.setTotalEventsCount(eventSummarys.size());
		dashboardDto.setTotalVolunteersCount(
				eventSummarys.stream().map(x -> x.getTotalNoOfVolunteer()).reduce(0, Integer::sum));
		dashboardDto
				.setLivesImpactedCount(eventSummarys.stream().map(x -> x.getLivesImpacted()).reduce(0, Integer::sum));

		List<String> eventIds = new ArrayList<String>();
		for (EventSummary eventSummary : eventSummarys) {
			eventIds.add(eventSummary.getEventId());
		}
		dashboardDto.setTotalParticipantCount(eventInformationDao.countByEventIdIn(eventIds));
		return dashboardDto;
	}

	@Override
	@Transactional
	public Page<EventSummary> getEventSummaryList(int page, UserDto userDto) {
		// TODO Auto-generated method stub
		Page<EventSummary> pageList = null;
		Pageable paging = PageRequest.of(page, 4);
		if (userDto.getRole() == 3) {
			pageList = eventSummaryDao.findByPocId(userDto.getEmployeeId(), paging);
		} else {
			pageList = eventSummaryDao.findAll(paging);
		}
		return pageList;
	}

	@Override
	@Transactional
	public EventSummary getEventDetails(String eventId, UserDto userDto) {
		return eventSummaryDao.findByEventId(eventId);
	}

	@Override
	@Transactional
	public Page<EventSummary> getEventSummaryListForReports(int page, UserDto userDto) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(page, 4);
//		return eventSummaryDao.findAll(paging);
		Page<EventSummary> pageList = null;
		if (userDto.getRole() == 3) {
			pageList = eventSummaryDao.findByPocId(userDto.getEmployeeId(), paging);
		} else {
			pageList = eventSummaryDao.findAll(paging);
		}
		return pageList;
	}

	@Override
	@Transactional
	public Response addPmo(String email) {
		// TODO Auto-generated method stub

		Response response = new Response();
		User user = userDao.findByEmail(email);
		if (Objects.nonNull(user)) {
			if (user.getRole() == 2) {
				response.setStatus("OK");
				response.setMessage("User is already a PMO");
			} else {
				user.setRole(2);
				userDao.save(user);
				response.setStatus("SUCCESS");
				response.setMessage("PMO Added/Assigned Successfully");
			}
		} else {
			response.setStatus("OK");
			response.setMessage("There is no user with the given email Id");
		}
		return response;
	}

	@Transactional
	public Page<User> getUserDetailPagination(int page) {
		Pageable paging = PageRequest.of(page, 5);
		return userDao.findByRole(2, paging);
	}

	@Override
	@Transactional
	public Response removePmo(String email) {
		// TODO Auto-generated method stub
		Response response = new Response();
		try {
			User user = userDao.findByEmail(email);
			if (Objects.nonNull(user)) {
				user.setRole(3);
				userDao.save(user);
				response.setStatus("SUCCESS");
				response.setMessage("User removed as PMO successfully");
			} else {
				response.setStatus("OK");
				response.setMessage("The user you are trying to remove is not a PMO");
			}
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("FAILURE");
			response.setMessage("Internal Server Error");
		}
		return response;
	}

	@Override
	@Transactional
	public Page<User> getPocsListPagination(int page) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(page, 10, Sort.by("email").ascending());
//		userDao.findByRoleOrderByEmailAsc(3, paging);
		int[] roles = { 3, 4 };
		return userDao.findByRoleIn(roles, paging);
	}

	@Override
	@Transactional
	public Response addPocEmail(int id, String email) {
		Response response = new Response();
		try {
			User user = userDao.getOne(id);
			user.setEmail(email);
			userDao.save(user);

			List<ParticipantFeedbackStatus> feedbackStatus = participantFeedbackStatusDao
					.findByEmployeeId(user.getEmployeeId());

			List<ParticipantFeedbackStatus> feedbackStatuses = new ArrayList<ParticipantFeedbackStatus>();
			for (ParticipantFeedbackStatus status : feedbackStatus) {
				status.setEmployeeEmail(email);
				feedbackStatuses.add(status);
			}
			participantFeedbackStatusDao.saveAll(feedbackStatuses);
			response.setStatus("SUCCESS");
			response.setMessage("POC or Participant Email Updated succesfully");
		} catch (Exception e) {
			// TODO: handle exception
			response.setStatus("FAILURE");
			response.setMessage("Internal Server Error");
		}
		return response;
	}

	@Override
	@Transactional
	public Response addRatingQuestion(String ratingQues, String likeQues, String dislikeQues) {
		// TODO Auto-generated method stub
		FeedbackRatingQuestion question = new FeedbackRatingQuestion();
		question.setRatingQuestion(ratingQues);
		question.setLikeQuestion(likeQues);
		question.setDislikeQuestion(dislikeQues);
		feedbackRatingQuestionDao.save(question);

		FeedbackQuestion feedbackQuestion = new FeedbackQuestion();
		feedbackQuestion.setQuestion(ratingQues);
		feedbackQuestion.setQuestionType("R");
		feedbackQuestion.setParticipantType("PC");
		feedbackQuestion.setFeedbackRatingQuestion(question);
		feedbackQuestionDao.save(feedbackQuestion);

		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Feedback question for participated user saved successfully");
		return response;
	}

	@Override
	@Transactional
	public Response addUnregisterNotAttendedQuestion(String question, String[] ans, String participationType) {
		// TODO Auto-generated method stub
		FeedbackQuestion feedbackQuestion = new FeedbackQuestion();
		feedbackQuestion.setQuestion(question);
		feedbackQuestion.setQuestionType("M");
		feedbackQuestion.setParticipantType(participationType);
		feedbackQuestionDao.save(feedbackQuestion);

		List<FeedbackChoiceAnswer> answers = new ArrayList<FeedbackChoiceAnswer>();

		for (String str : ans) {
			FeedbackChoiceAnswer choiceAnswer = new FeedbackChoiceAnswer();
			choiceAnswer.setQuestion(feedbackQuestion);
			choiceAnswer.setAnswer(str);
			answers.add(choiceAnswer);
		}
		feedbackChoiceAnswerDao.saveAll(answers);
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Questions added successfully");
		return response;
	}

	@Override
	@Transactional
	public Page<FeedbackQuestion> getFeedbackQuestions(int page) {
		// TODO Auto-generated method stub
		Pageable paging = PageRequest.of(page, 5);
		return feedbackQuestionDao.findAll(paging);
	}

	@Override
	@Transactional
	public Response sendEmailsForFeedback(String eventId) throws AddressException, MessagingException, IOException {
		// TODO Auto-generated method stub

		List<ParticipantFeedbackStatus> participantFeedbackStatus = null;
		List<ParticipantFeedbackStatus> participantFeedbackStatusNew = new ArrayList<ParticipantFeedbackStatus>();
		if (!(eventId.isEmpty())) {
			participantFeedbackStatus = participantFeedbackStatusDao.findByEventIdAndIsFeedbackSent(eventId, 0);
		} else {
			participantFeedbackStatus = participantFeedbackStatusDao.findByIsFeedbackSent(0);
		}

		List<String> randomList = new ArrayList<String>();
		for (ParticipantFeedbackStatus feedbackStatus : participantFeedbackStatus) {
			if (Objects.nonNull(feedbackStatus.getEmployeeEmail())) {
				String random = getSaltString();
				while (randomList.contains(random)) {
					random = getSaltString();
				}
				randomList.add(random);

				String feedbackUrl = emailProperties.getNodeUrl() + "/feedbackForm?eventId="
						+ feedbackStatus.getEventId() + "&employeeId=" + feedbackStatus.getEmployeeId() + "&secretCode="
						+ random;
				EventSummary eventSummary = eventSummaryDao.findByEventId(feedbackStatus.getEventId());
				String emailTemplate = emailTemplateProperties.getFeedback();
				emailTemplate = emailTemplate
						.replaceAll("\\[\\[eventName\\]\\]", "<b>" + eventSummary.getEventName() + "</b>")
						.replaceAll("\\[\\[url\\]\\]", feedbackUrl);
				emailService.sendmailForFeedback(feedbackStatus.getEmployeeEmail(), "Feedback Submission Email",
						emailTemplate);
				feedbackStatus.setRandomValue(random);
				feedbackStatus.setIsFeedbackSent(1);
				participantFeedbackStatusNew.add(feedbackStatus);
			}
		}

		participantFeedbackStatusDao.saveAll(participantFeedbackStatusNew);
		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Email for feedback send successfully");
		return response;
	}

	protected String getSaltString() {
		String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
		StringBuilder salt = new StringBuilder();
		Random rnd = new Random();
		while (salt.length() < 50) { // length of the random string.
			int index = (int) (rnd.nextFloat() * SALTCHARS.length());
			salt.append(SALTCHARS.charAt(index));
		}
		String saltStr = salt.toString();
		return saltStr;

	}

	@Override
	@Transactional
	public List<EventSummary> getEventList(UserDto userDto) {
		// TODO Auto-generated method stub

		List<EventSummary> eventSummaries = null;
		if (userDto.getRole() == 3) {
			eventSummaries = eventSummaryDao.findByPocId(userDto.getEmployeeId());
		} else {
			eventSummaries = eventSummaryDao.findAll();
		}
		return eventSummaries;
	}

	@Override
	public Response sendEmailReportAttachment(String email, ByteArrayOutputStream bos)
			throws AddressException, MessagingException, IOException {
		// TODO Auto-generated method stub
		User user = userDao.findByEmail(email);
		Response response = new Response();
		if (Objects.nonNull(user)) {
			emailService.sendEmailReportAttachment(email, "Report Attachment", bos);
			response.setStatus("SUCCESS");
			response.setMessage("Attachment Send to the " + email + " email");
		} else {
			response.setStatus("FAILURE");
			response.setMessage("The given email is not a valid email");
		}
		return response;
	}

	@Override
	public List<User> getPmo() {
		// TODO Auto-generated method stub
		List<User> users = userDao.findByRole(2);
		return users;
	}

	@Override
	public Response getFeedbackFormDetails(String eventId, String employeeId, String secretCode) {
		// TODO Auto-generated method stub
		Response response = new Response();
		if (Objects.nonNull(participantFeedbackStatusDao.findByEventIdAndEmployeeIdAndIsFeedbackCompletedAndRandomValue(
				eventId, Integer.parseInt(employeeId), 1, secretCode))) {
			response.setStatus("COMPLETED");
			response.setMessage("Many Thanks for the Feedback");
		} else {
			ParticipantFeedbackStatus feedbackStatus = participantFeedbackStatusDao
					.findByEventIdAndEmployeeIdAndRandomValue(eventId, Integer.parseInt(employeeId), secretCode);
			EventSummary eventSummary = eventSummaryDao.findByEventId(eventId);
			response.setEventName(eventSummary.getEventName());
			if (Objects.nonNull(feedbackStatus)) {
				if (feedbackStatus.getEventStatus().equals("NOT ATTENDED")
						|| feedbackStatus.getEventStatus().equals("UNREGISTERED")) {
					String qtype = feedbackStatus.getEventStatus().equals("NOT ATTENDED") ? "NA" : "UN";
					List<FeedbackQuestion> feedbackQuestions = feedbackQuestionDao.findByParticipantType(qtype);
					Collections.shuffle(feedbackQuestions);
					int choiceAnswerId = feedbackQuestions.get(0).getId();

					List<FeedbackChoiceAnswer> feedbackChoiceAnswers = feedbackChoiceAnswerDao
							.findByQuestionId(choiceAnswerId);
					response.setStatus(qtype);
					response.setMessage("Feedback question");
					response.setFeedbackChoiceAnswers(feedbackChoiceAnswers);
				} else {
					String qtype = feedbackStatus.getEventStatus().equals("PARTICIPATED") ? "PC" : "";
					List<FeedbackQuestion> feedbackQuestions = feedbackQuestionDao.findByParticipantType(qtype);
					Collections.shuffle(feedbackQuestions);

					response.setStatus(qtype);
					response.setMessage("Feedback question");
					response.setFeedbackRatingQuestion(feedbackQuestions.get(0).getFeedbackRatingQuestion());
				}
			} else {
				response.setStatus("OK");
				response.setMessage("Link Invalid or Expired");
			}
		}
		return response;
	}

	@Override
	public Response submitFeedback(FeedbackAnswerResponse feedbackAnswerResponse) {
		// TODO Auto-generated method stub
		UserFeedbackAnswer userFeedbackAnswer = new UserFeedbackAnswer();

		userFeedbackAnswer.setEmployeeId(feedbackAnswerResponse.getEmployeeId());
		userFeedbackAnswer.setEventId(feedbackAnswerResponse.getEventId());
		userFeedbackAnswer.setFeedbackType(feedbackAnswerResponse.getFeedbackType());
		userFeedbackAnswer.setQuestionId(feedbackAnswerResponse.getQuestionId());

		if (feedbackAnswerResponse.getFeedbackType().equals("UN")
				|| feedbackAnswerResponse.getFeedbackType().equals("NA")) {
			userFeedbackAnswer.setChoiceAnswer(feedbackAnswerResponse.getChoiceAnswer());
		} else {
			userFeedbackAnswer.setRating(feedbackAnswerResponse.getRating());
			userFeedbackAnswer.setLikeAnswer(feedbackAnswerResponse.getLikeAnswer());
			userFeedbackAnswer.setDislikeAnswer(feedbackAnswerResponse.getDislikeAnswer());
			List<UserFeedbackAnswer> answers = userFeedbackAnswerDao.findByEventId(feedbackAnswerResponse.getEventId());
			int overallRating = 0;
			if (!answers.isEmpty()) {
				for (UserFeedbackAnswer answer : answers) {
					overallRating += answer.getRating();
				}
				overallRating = (overallRating + feedbackAnswerResponse.getRating());
				overallRating = (overallRating / (answers.size() + 1));
			} else {
				overallRating = feedbackAnswerResponse.getRating();
			}
			EventSummary eventSummary = eventSummaryDao.findByEventId(feedbackAnswerResponse.getEventId());
			eventSummary.setOverallRating(overallRating);
			eventSummaryDao.save(eventSummary);
		}
		userFeedbackAnswer.setDate(new Date());
		userFeedbackAnswerDao.save(userFeedbackAnswer);

		ParticipantFeedbackStatus feedbackStatus = participantFeedbackStatusDao
				.findByEventIdAndEmployeeIdAndRandomValue(feedbackAnswerResponse.getEventId(),
						feedbackAnswerResponse.getEmployeeId(), feedbackAnswerResponse.getSecretCode());
		feedbackStatus.setIsFeedbackCompleted(1);
		participantFeedbackStatusDao.save(feedbackStatus);

		Response response = new Response();
		response.setStatus("SUCCESS");
		response.setMessage("Many Thanks for the Feedback");
		return response;
	}

	@Override
	public Response getEventFeedbackDetails(String eventId, UserDto userDto) {
		// TODO Auto-generated method stub
		Response response = new Response();
		EventSummary eventSummary = eventSummaryDao.findByEventId(eventId);
		response.setAverageRating(eventSummary.getOverallRating());
		List<UserFeedbackAnswer> answers = userFeedbackAnswerDao.findByEventId(eventId);
		response.setUserFeedbackAnswers(answers);
		return response;
	}

}
