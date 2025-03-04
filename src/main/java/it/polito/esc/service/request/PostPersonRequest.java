package it.polito.esc.service.request;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import it.polito.esc.bean.PersonUpdateView;

public class PostPersonRequest extends EscRequest {

	private static final long serialVersionUID = 4121992200620241603L;

	/**
	 * The HTTP method of the request
	 */
	private static final String METHOD = "POST";

	/**
	 * The endpoint of the request
	 */
	private static final String ENDPOINT = "/persons";

	private PersonUpdateView personUpdateView;

	public PostPersonRequest(String baseServiceUrl, String apiKey, PersonUpdateView personUpdateView) {
		super(baseServiceUrl, apiKey);
		this.setPersonUpdateView(personUpdateView);
	}

	public PersonUpdateView getPersonUpdateView() {
		return personUpdateView;
	}

	public void setPersonUpdateView(PersonUpdateView personUpdateView) {
		if (personUpdateView == null)
			throw new IllegalArgumentException("The person update view cannot be null");
		this.personUpdateView = personUpdateView;
	}

	@Override
	public String getEndpoint() {
		return this.baseServiceUrl + ENDPOINT;
	}

	@Override
	public String getMethod() {
		return METHOD;
	}

	@Override
	public String getJson() {
		return GSON.toJson(this.getPersonUpdateView());
	}

	@Override
	public BodyPublisher getBody() {
		return HttpRequest.BodyPublishers.ofString(getJson());
	}

	@Override
	public String toString() {
		return "PostPersonRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", baseServiceUrl=" + baseServiceUrl
				+ ", apiKey=" + apiKey + "]";
	}

	@Override
	public EscRequest clone() {
		return new PostPersonRequest(this.baseServiceUrl, this.apiKey,
				this.personUpdateView == null ? null : this.personUpdateView.clone());
	}

}
