package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class GetCardStatusRequest extends EscRequest {

	private static final long serialVersionUID = 4121992051220241327L;

	private static final String METHOD = "GET";

	private static final String ENDPOINT = "/cards/%s/status";

	/**
	 * Path variable
	 * 
	 * The card number
	 */
	private String escn;

	public GetCardStatusRequest(String baseServiceUrl, String apiKey, String escn) {
		super(baseServiceUrl, apiKey);
		this.setEscn(escn);
	}

	public String getEscn() {
		return escn;
	}

	public void setEscn(String escn) {
		if (escn == null || escn.isBlank())
			throw new IllegalArgumentException("The european student card number cannot be null or empty");
		this.escn = escn;
	}

	@Override
	public String getEndpoint() {
		return this.baseServiceUrl + String.format(ENDPOINT, this.getEscn());
	}

	@Override
	public String getMethod() {
		return METHOD;
	}

	@Override
	public BodyPublisher getBody() {
		return BodyPublishers.noBody();
	}

	@Override
	public String getJson() {
		return null;
	}

	@Override
	public String toString() {
		return "GetCardStatusRequest [escn=" + escn + "]";
	}

	@Override
	public EscRequest clone() {
		return new GetCardStatusRequest(this.baseServiceUrl, this.apiKey, this.escn);
	}

}
