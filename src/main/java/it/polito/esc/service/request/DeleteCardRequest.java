package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class DeleteCardRequest extends EscRequest {

	private static final long serialVersionUID = 4121992041220241542L;

	private static final String METHOD = "DELETE";

	private static final String ENDPOINT = "/cards/%s";

	/**
	 * Path variable
	 */
	private String escn;

	public DeleteCardRequest(String baseServiceUrl, String apiKey, String escn) {
		super(baseServiceUrl, apiKey);
		this.setEscn(escn);
	}

	public String getEsc() {
		return escn;
	}

	public void setEscn(String escn) {
		if (escn == null || escn.isBlank())
			throw new IllegalArgumentException("The european student card number cannot be null or empty.");
		this.escn = escn;
	}

	@Override
	public String getEndpoint() {
		return this.baseServiceUrl + String.format(ENDPOINT, this.getEsc());
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
		return "DeleteCardRequest [escn=" + escn + "]";
	}

	@Override
	public EscRequest clone() {
		return new DeleteCardRequest(this.baseServiceUrl, this.apiKey, this.escn);
	}

}
