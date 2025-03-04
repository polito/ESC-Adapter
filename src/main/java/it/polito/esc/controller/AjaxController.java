package it.polito.esc.controller;

import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.MessageSource;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import it.polito.esc.bean.CardUpdateView;
import it.polito.esc.bean.PersonUpdateView;
import it.polito.esc.service.request.DeleteCardRequest;
import it.polito.esc.service.request.DeletePersonRequest;
import it.polito.esc.service.request.GetCardQrRequest;
import it.polito.esc.service.request.GetCardRequest;
import it.polito.esc.service.request.GetCardStatusRequest;
import it.polito.esc.service.request.GetCardsRequest;
import it.polito.esc.service.request.GetPersonRequest;
import it.polito.esc.service.request.GetPersonsRequest;
import it.polito.esc.service.request.PostCardRequest;
import it.polito.esc.service.request.PostPersonRequest;
import it.polito.esc.service.request.PutCardRequest;
import it.polito.esc.service.request.PutPersonRequest;
import it.polito.esc.service.response.GenericResponse;
import it.polito.esc.service.response.GetCardQrResponse;
import it.polito.esc.service.response.GetCardResponse;
import it.polito.esc.service.response.GetCardStatusResponse;
import it.polito.esc.service.response.GetCardsResponse;
import it.polito.esc.service.response.GetPersonResponse;
import it.polito.esc.service.response.GetPersonsResponse;
import it.polito.esc.service.response.PostCardResponse;
import it.polito.esc.service.response.PostPersonResponse;
import it.polito.esc.service.response.PutCardResponse;
import it.polito.esc.service.response.PutPersonResponse;
import it.polito.esc.util.EuropeanStudentCardService;

@RestController
@ControllerAdvice
@RequestMapping("/service")
public class AjaxController {

	private static final Logger logger = LoggerFactory.getLogger(AjaxController.class.getName());

	private static final String APPLICATION_JSON = "application/json";
	private static final String UTF_8 = "UTF-8";

	@Autowired
	MessageSource messageSource;

	@Value("${esc.baseUrl}")
	String escBaseUrl;

	@Value("${esc.path.rest.api}")
	String escPathRestApi;

	@Value("${esc.key}")
	String escKey;

	@RequestMapping(value = "/student", method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PostPersonResponse studentPost(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestBody PersonUpdateView personUpdateView) {
		logger.info("studentPost: personUpdateView = " + personUpdateView);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		PostPersonRequest postPersonRequest = new PostPersonRequest(escBaseUrl + escPathRestApi, escKey,
				personUpdateView);
		PostPersonResponse postPersonResponse = EuropeanStudentCardService.postPerson(postPersonRequest);
		response.setStatus(postPersonResponse.getStatusCode());
		logger.info("postPerson: response = " + postPersonResponse);
		return postPersonResponse;
	}

	@RequestMapping(value = "/student", method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GetPersonResponse studentGet(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "esi", required = true) String esi) {
		logger.info("studentGet: esi = " + esi);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		GetPersonRequest getPersonRequest = new GetPersonRequest(escBaseUrl + escPathRestApi, escKey, esi);
		GetPersonResponse getPersonResponse = EuropeanStudentCardService.getPerson(getPersonRequest);
		response.setStatus(getPersonResponse.getStatusCode());
		logger.info("getPerson: response = " + getPersonResponse);
		return getPersonResponse;
	}

	@RequestMapping(value = "/students", method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GetPersonsResponse studentsGet(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "search", required = false) String search) {
		logger.info(String.format("studentGet parameters: sort = %s, direction = %s, page = %s, size = %s, search = %s",
				sort, direction, page, size, search));
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		GetPersonsRequest getPersonsRequest = new GetPersonsRequest(escBaseUrl + escPathRestApi, escKey, sort,
				direction, page, size, search);
		GetPersonsResponse getPersonsResponse = EuropeanStudentCardService.getPersons(getPersonsRequest);
		response.setStatus(getPersonsResponse.getStatusCode());
		logger.info("getPersons: response = " + getPersonsResponse);
		return getPersonsResponse;
	}

	@RequestMapping(value = "/student", method = RequestMethod.PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PutPersonResponse studentPut(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "esi", required = true) String esi, @RequestBody PersonUpdateView personUpdateView) {
		logger.info("studentPut: personUpdateView = " + personUpdateView);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		PutPersonRequest putPersonRequest = new PutPersonRequest(escBaseUrl + escPathRestApi, escKey, esi,
				personUpdateView);
		PutPersonResponse putPersonResponse = EuropeanStudentCardService.putPerson(putPersonRequest);
		response.setStatus(putPersonResponse.getStatusCode());
		logger.info("putPerson: response = " + putPersonResponse);
		return putPersonResponse;
	}

	@RequestMapping(value = "/student", method = RequestMethod.DELETE, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GenericResponse studentDelete(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "esi", required = true) String esi) {
		logger.info("studentDelete: esi = " + esi);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		DeletePersonRequest deleteRequest = new DeletePersonRequest(escBaseUrl + escPathRestApi, escKey, esi);
		GenericResponse deleteResponse = EuropeanStudentCardService.deletePerson(deleteRequest);
		response.setStatus(deleteResponse.getStatusCode());
		logger.info("deletePerson: response = " + deleteResponse);
		return deleteResponse;
	}

	@RequestMapping(value = "/studentCard", method = RequestMethod.POST, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PostCardResponse studentCardPost(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestBody CardUpdateView cardUpdateView) {
		logger.info("studentCardPost: cardUpdateView = " + cardUpdateView);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		PostCardRequest postCardRequest = new PostCardRequest(escBaseUrl + escPathRestApi, escKey, cardUpdateView);
		PostCardResponse postCardResponse = EuropeanStudentCardService.postCard(postCardRequest);
		response.setStatus(postCardResponse.getStatusCode());
		logger.info("postCard: response = " + postCardResponse);
		return postCardResponse;
	}

	@RequestMapping(value = "/studentCard", method = RequestMethod.PUT, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public PutCardResponse studentCardPut(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "escn", required = true) String escn, @RequestBody CardUpdateView cardUpdateView) {
		logger.info("studentCardPut: cardUpdateView = " + cardUpdateView);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		PutCardRequest putCardRequest = new PutCardRequest(escBaseUrl + escPathRestApi, escKey, escn, cardUpdateView);
		PutCardResponse putCardResponse = EuropeanStudentCardService.putCard(putCardRequest);
		response.setStatus(putCardResponse.getStatusCode());
		logger.info("putCard: response = " + putCardResponse);
		return putCardResponse;
	}

	@RequestMapping(value = "/studentCard", method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GetCardResponse studentCardGet(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "escn", required = true) String escn) {
		logger.info("studentCardGet: escn = " + escn);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		GetCardRequest getPersonRequest = new GetCardRequest(escBaseUrl + escPathRestApi, escKey, escn);
		GetCardResponse getCardResponse = EuropeanStudentCardService.getCard(getPersonRequest);
		response.setStatus(getCardResponse.getStatusCode());
		logger.info("getPerson: response = " + getCardResponse);
		return getCardResponse;
	}

	@RequestMapping(value = "/studentCards", method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GetCardsResponse studentCardsGet(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "sort", required = false) String sort,
			@RequestParam(value = "direction", required = false) String direction,
			@RequestParam(value = "page", required = false) Integer page,
			@RequestParam(value = "size", required = false) Integer size,
			@RequestParam(value = "search", required = false) String search) {
		logger.info(
				String.format("studentCards parameters: sort = %s, direction = %s, page = %s, size = %s, search = %s",
						sort, direction, page, size, search));
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		GetCardsRequest getCardsRequest = new GetCardsRequest(escBaseUrl + escPathRestApi, escKey, sort, direction,
				page, size, search);
		GetCardsResponse getCardsResponse = EuropeanStudentCardService.getCards(getCardsRequest);
		response.setStatus(getCardsResponse.getStatusCode());
		logger.info("getCards: response = " + getCardsResponse);
		return getCardsResponse;
	}

	@RequestMapping(value = "/studentCardStatus", method = RequestMethod.GET, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GetCardStatusResponse studentCardStatusGet(HttpServletRequest request, HttpServletResponse response,
			Locale locale, @RequestParam(value = "escn", required = true) String escn) {
		logger.info("studentCardStatus: escn = " + escn);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		GetCardStatusRequest getCardStatusRequest = new GetCardStatusRequest(escBaseUrl + escPathRestApi, escKey, escn);
		GetCardStatusResponse getCardStatusResponse = EuropeanStudentCardService.getCardStatus(getCardStatusRequest);
		response.setStatus(getCardStatusResponse.getStatusCode());
		logger.info("getPerson: response = " + getCardStatusResponse);
		return getCardStatusResponse;
	}

	@RequestMapping(value = "/studentCardQr", method = RequestMethod.GET, produces = { GetCardQrRequest.MEDIA_TYPE_SVG,
			GetCardQrRequest.MEDIA_TYPE_TEXT })
	public String studentCardQrGet(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "escn", required = true) String escn,
			@RequestParam(value = "orientation", required = false) String orientation,
			@RequestParam(value = "colours", required = false) String colours,
			@RequestParam(value = "size", required = false) String size) {
		logger.info("studentCardQr: escn = " + escn);
		GetCardQrRequest getCardQrRequest = new GetCardQrRequest(escBaseUrl + escPathRestApi, escKey, escn,
				request.getHeader("Accept"), orientation, colours, size);
		GetCardQrResponse getCardQrResponse = EuropeanStudentCardService.getCardQr(getCardQrRequest);
		response.setContentType(getCardQrRequest.getAccept());
		response.setStatus(getCardQrResponse.getStatusCode());
		logger.info("getCardQr: response = " + getCardQrResponse);
		return getCardQrResponse.getData();
	}

	@RequestMapping(value = "/studentCard", method = RequestMethod.DELETE, consumes = APPLICATION_JSON, produces = APPLICATION_JSON)
	public GenericResponse studentCardDelete(HttpServletRequest request, HttpServletResponse response, Locale locale,
			@RequestParam(value = "escn", required = true) String escn) {
		logger.info("studentCardDelete: escn = " + escn);
		response.setCharacterEncoding(UTF_8);
		response.setContentType(APPLICATION_JSON);
		DeleteCardRequest deleteRequest = new DeleteCardRequest(escBaseUrl + escPathRestApi, escKey, escn);
		GenericResponse deleteResponse = EuropeanStudentCardService.deleteCard(deleteRequest);
		response.setStatus(deleteResponse.getStatusCode());
		logger.info("deleteCard: response = " + deleteResponse);
		return deleteResponse;
	}

}
