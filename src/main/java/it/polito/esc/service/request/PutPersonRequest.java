package it.polito.esc.service.request;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import it.polito.esc.bean.PersonUpdateView;

public class PutPersonRequest extends EscRequest {

	private static final long serialVersionUID = 4121992200620241525L;

	/**
	 * The HTTP method of the request
	 */
	private static final String METHOD = "PUT";

	/**
	 * The endpoint of the request
	 */
	private static final String ENDPOINT = "/persons/%s";

	/**
	 * Path variable
	 */
	private String esi;

	private PersonUpdateView personUpdateView;

	public PutPersonRequest(String baseServiceUrl, String apiKey, String esi, PersonUpdateView personUpdateView) {
		super(baseServiceUrl, apiKey);
		this.setEsi(esi);
		this.setPersonUpdateView(personUpdateView);
	}

	public String getEsi() {
		return esi;
	}

	public void setEsi(String esi) {
		if (esi == null || esi.isBlank())
			throw new IllegalArgumentException("The id cannot be null or empty");
		this.esi = esi;
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
		return this.baseServiceUrl + String.format(ENDPOINT, this.getEsi());
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
		return "PutPersonRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", esi=" + esi + ", baseServiceUrl="
				+ baseServiceUrl + ", apiKey=" + apiKey + "]";
	}

	@Override
	public EscRequest clone() {
		return new PutPersonRequest(this.baseServiceUrl, this.apiKey, this.esi,
				this.personUpdateView == null ? null : this.personUpdateView.clone());
	}

}
