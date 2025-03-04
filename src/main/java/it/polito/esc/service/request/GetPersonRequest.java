package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class GetPersonRequest extends EscRequest {

	private static final long serialVersionUID = 4121992041220241542L;

	private static final String METHOD = "GET";

	private static final String ENDPOINT = "/persons/%s";

	/**
	 * Path variable
	 */
	private String esi;

	public GetPersonRequest(String baseServiceUrl, String apiKey, String esi) {
		super(baseServiceUrl, apiKey);
		this.setId(esi);
	}

	public String getEsi() {
		return esi;
	}

	public void setId(String esi) {
		if (esi == null || esi.isBlank())
			throw new IllegalArgumentException("The id cannot be null or empty");
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
		return "GetPersonsRequest [esi=" + esi + "]";
	}

	@Override
	public EscRequest clone() {
		return new GetPersonRequest(this.baseServiceUrl, this.apiKey, this.esi);
	}

}
