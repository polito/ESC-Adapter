package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class DeletePersonRequest extends EscRequest {

	private static final long serialVersionUID = 4121992041220241542L;

	private static final String METHOD = "DELETE";

	private static final String ENDPOINT = "/persons/%s";

	/**
	 * Path variable
	 */
	private String esi;

	public DeletePersonRequest(String baseServiceUrl, String apiKey, String esi) {
		super(baseServiceUrl, apiKey);
		this.setEsi(esi);
	}

	public String getEsi() {
		return esi;
	}

	public void setEsi(String esi) {
		if (esi == null || esi.isBlank())
			throw new IllegalArgumentException("The european student identifier must be not null and not empty.");
		this.esi = esi;
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
	public BodyPublisher getBody() {
		return BodyPublishers.noBody();
	}

	@Override
	public String getJson() {
		return null;
	}

	@Override
	public String toString() {
		return "DeletePersonRequest [esi=" + esi + "]";
	}

	@Override
	public EscRequest clone() {
		return new DeletePersonRequest(this.baseServiceUrl, this.apiKey, this.esi);
	}

}
