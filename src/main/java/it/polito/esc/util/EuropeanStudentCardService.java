package it.polito.esc.util;

import java.io.Serializable;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpClient.Builder;
import java.net.http.HttpClient.Redirect;
import java.net.http.HttpClient.Version;
import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpResponse;
import java.net.http.HttpResponse.BodyHandlers;
import java.time.Duration;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import it.polito.esc.bean.CardStatusView;
import it.polito.esc.bean.CardView;
import it.polito.esc.bean.HttpResponseCustom;
import it.polito.esc.bean.PagedResourcesCardLiteView;
import it.polito.esc.bean.PagedResourcesPersonLiteView;
import it.polito.esc.bean.PersonView;
import it.polito.esc.exception.EscException;
import it.polito.esc.service.request.DeleteCardRequest;
import it.polito.esc.service.request.DeletePersonRequest;
import it.polito.esc.service.request.EscRequest;
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
import it.polito.esc.service.response.EscErrorResponse;
import it.polito.esc.service.response.EscResponse;
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

public class EuropeanStudentCardService implements Serializable {

	private static final Logger logger = LoggerFactory.getLogger(EuropeanStudentCardService.class.getName());

	private static final long serialVersionUID = 4121992140620241518L;

	private static final String HEADER_CONTENT_TYPE = "Content-Type";
	private static final String HEADER_ACCEPT = "Accept";
	private static final String HEADER_AUTHORIZATION = "Authorization";
	private static final String CONTENT_TYPE_JSON = "application/json";

	@SuppressWarnings("unchecked")
	private static <S, T> HttpResponseCustom<EscResponse<T>> callEscService(EscRequest escRequest,
			EscResponse<T> outputObject, Class<T> clazz) throws EscException {
		try {

			Gson gson = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
					.setDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSX").create();

			try {
				logger.info("Call to the ESC service: \n\t- URL: " + escRequest.getEndpoint() + "\n\t- Method: "
						+ escRequest.getMethod()
						+ (escRequest.getJson() != null ? "\n\t- Input: " + escRequest.getJson() : ""));
			} catch (Exception e) {
			}

			if (outputObject == null)
				throw new EscException("Error in the call to the service: OutputObject cannot be null");

			BodyPublisher payload = escRequest.getBody();

			Builder builder = HttpClient.newBuilder().version(Version.HTTP_1_1).followRedirects(Redirect.NORMAL)
					.connectTimeout(Duration.ofSeconds(20));

			HttpClient client = builder.build();

			java.net.http.HttpRequest.Builder requestBuilder = HttpRequest.newBuilder()
					.uri(URI.create(escRequest.getEndpoint())).method(escRequest.getMethod(), payload)
					.header(HEADER_CONTENT_TYPE, CONTENT_TYPE_JSON)
					.setHeader(HEADER_AUTHORIZATION, escRequest.getAuthorization());

			if (escRequest.getHeaderAccept() != null)
				requestBuilder.setHeader(HEADER_ACCEPT, escRequest.getHeaderAccept());

			HttpRequest request = requestBuilder.build();

			logger.info("Sending call to the ESC service");

			HttpResponse<String> response = client.send(request, BodyHandlers.ofString());

			try {
				logger.info("Response: \n\t- Code: " + response.statusCode() + "\n\t- Body: "
						+ (response.body() == null ? "empty" : response.body()));
			} catch (Exception e) {
			}

			if (response.body() != null && !response.body().isEmpty()) {
				if (response.headers().firstValue(HEADER_CONTENT_TYPE).get().contains(CONTENT_TYPE_JSON)) {
					JsonObject json = JsonParser.parseString(response.body().toString()).getAsJsonObject();
					if (response.statusCode() >= HttpStatus.OK.value()
							&& response.statusCode() < HttpStatus.MULTIPLE_CHOICES.value())
						outputObject.setData((T) gson.fromJson(json, clazz));
					else
						outputObject.setEscErrorResponse(gson.fromJson(json, EscErrorResponse.class));
				} else
					outputObject.setData((T) response.body().toString());
			}
			outputObject.setStatusCode(response.statusCode());

			logger.info("Service completed successfully");

			return new HttpResponseCustom<EscResponse<T>>(response, outputObject);
		} catch (EscException e) {
			try {
				logger.error("Error in the call to the service: \n\t- URL: " + escRequest.getEndpoint()
						+ "\n\t- Method: " + escRequest.getMethod()
						+ (escRequest.getJson() != null ? "\n\t- Input: " + escRequest.getJson() : ""), e);
				logger.info("Service completed with error");
			} catch (Exception e1) {
			}
			throw e;
		} catch (Exception e) {
			try {
				logger.error("Error in the call to the service: \n\t- URL: " + escRequest.getEndpoint()
						+ "\n\t- Method: " + escRequest.getMethod()
						+ (escRequest.getJson() != null ? "\n\t- Input: " + escRequest.getJson() : ""), e);
			} catch (Exception e1) {
			}
			outputObject.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			outputObject.setEscErrorResponse(
					new EscErrorResponse("call_failed", "Error in the call to the service: " + e.getMessage()));
			logger.info("Service completed with error");
			return new HttpResponseCustom<EscResponse<T>>(null, outputObject);
		}
	}

	//////////////////////////////////////// PERSONS SERVICE ////////////////////////////////////////

	public static PutPersonResponse putPerson(PutPersonRequest putPersonsRequest) {
		try {
			return (PutPersonResponse) callEscService(putPersonsRequest, new PutPersonResponse(), PersonView.class)
					.getOutputObject();
		} catch (EscException e) {
			PutPersonResponse putPersonResponse = new PutPersonResponse();
			putPersonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			putPersonResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return putPersonResponse;
		}
	}

	public static GetPersonResponse getPerson(GetPersonRequest getPersonRequest) {
		try {
			return (GetPersonResponse) callEscService(getPersonRequest, new GetPersonResponse(), PersonView.class)
					.getOutputObject();
		} catch (EscException e) {
			GetPersonResponse getPersonResponse = new GetPersonResponse();
			getPersonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getPersonResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getPersonResponse;
		}
	}

	public static GenericResponse deletePerson(DeletePersonRequest deletePersonRequest) {
		try {
			return (GenericResponse) callEscService(deletePersonRequest, new GenericResponse(), Object.class)
					.getOutputObject();
		} catch (EscException e) {
			GenericResponse deletePersonResponse = new GenericResponse();
			deletePersonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			deletePersonResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return deletePersonResponse;
		}
	}

	public static PostPersonResponse postPerson(PostPersonRequest postPersonRequest) {
		try {
			return (PostPersonResponse) callEscService(postPersonRequest, new PostPersonResponse(), PersonView.class)
					.getOutputObject();
		} catch (EscException e) {
			PostPersonResponse postPersonResponse = new PostPersonResponse();
			postPersonResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			postPersonResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return postPersonResponse;
		}
	}

	public static GetPersonsResponse getPersons(GetPersonsRequest getPersonsRequest) {
		try {
			return (GetPersonsResponse) callEscService(getPersonsRequest, new GetPersonsResponse(),
					PagedResourcesPersonLiteView.class).getOutputObject();
		} catch (EscException e) {
			GetPersonsResponse getPersonsResponse = new GetPersonsResponse();
			getPersonsResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getPersonsResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getPersonsResponse;
		}
	}

	//////////////////////////////////////// CARD SERVICE ////////////////////////////////////////

	public static GetCardStatusResponse getCardStatus(GetCardStatusRequest getCardStatusRequest) {
		try {
			return (GetCardStatusResponse) callEscService(getCardStatusRequest, new GetCardStatusResponse(),
					CardStatusView.class).getOutputObject();
		} catch (EscException e) {
			GetCardStatusResponse getCardStatusResponse = new GetCardStatusResponse();
			getCardStatusResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getCardStatusResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getCardStatusResponse;
		}
	}

	public static GetCardQrResponse getCardQr(GetCardQrRequest getCardQrRequest) {
		try {
			return (GetCardQrResponse) callEscService(getCardQrRequest, new GetCardQrResponse(), String.class)
					.getOutputObject();
		} catch (EscException e) {
			GetCardQrResponse getCardQrResponse = new GetCardQrResponse();
			getCardQrResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getCardQrResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getCardQrResponse;
		}
	}

	public static PutCardResponse putCard(PutCardRequest putCardRequest) {
		try {
			return (PutCardResponse) callEscService(putCardRequest, new PutCardResponse(), CardView.class)
					.getOutputObject();
		} catch (EscException e) {
			PutCardResponse putCardResponse = new PutCardResponse();
			putCardResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			putCardResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return putCardResponse;
		}
	}

	public static GetCardResponse getCard(GetCardRequest getCardRequest) {
		try {
			return (GetCardResponse) callEscService(getCardRequest, new GetCardResponse(), CardView.class)
					.getOutputObject();
		} catch (EscException e) {
			GetCardResponse getCardResponse = new GetCardResponse();
			getCardResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getCardResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getCardResponse;
		}
	}

	public static GenericResponse deleteCard(DeleteCardRequest deleteCardRequest) {
		try {
			return (GenericResponse) callEscService(deleteCardRequest, new GenericResponse(), Object.class)
					.getOutputObject();
		} catch (EscException e) {
			GenericResponse deleteCardResponse = new GenericResponse();
			deleteCardResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			deleteCardResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return deleteCardResponse;
		}
	}

	public static PostCardResponse postCard(PostCardRequest postCardRequest) {
		try {
			return (PostCardResponse) callEscService(postCardRequest, new PostCardResponse(), CardView.class)
					.getOutputObject();
		} catch (EscException e) {
			PostCardResponse postCardResponse = new PostCardResponse();
			postCardResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			postCardResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return postCardResponse;
		}
	}

	public static GetCardsResponse getCards(GetCardsRequest getCardsRequest) {
		try {
			return (GetCardsResponse) callEscService(getCardsRequest, new GetCardsResponse(),
					PagedResourcesCardLiteView.class).getOutputObject();
		} catch (EscException e) {
			GetCardsResponse getCardsResponse = new GetCardsResponse();
			getCardsResponse.setStatusCode(HttpStatus.INTERNAL_SERVER_ERROR.value());
			getCardsResponse.setEscErrorResponse(new EscErrorResponse("call_failed", e.getMessage()));
			return getCardsResponse;
		}
	}

	////////////////////////////////////////////////////////////////////////////////////////////////////////////

}
