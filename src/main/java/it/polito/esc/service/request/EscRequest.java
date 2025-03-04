package it.polito.esc.service.request;

import java.io.Serializable;
import java.net.http.HttpRequest.BodyPublisher;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public abstract class EscRequest implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992091220241255L;

	protected static final Gson GSON = new GsonBuilder().excludeFieldsWithoutExposeAnnotation()
			.setDateFormat("yyyy-MM-dd").create();

	private static final String AUTHORIZATION_HEADER = "Bearer %s";

	private static final String TYPE_JSON = "application/json";

	protected String baseServiceUrl;

	/**
	 * The key to access the service.
	 */
	protected String apiKey;

	public EscRequest(String baseServiceUrl, String apiKey) {
		super();
		this.baseServiceUrl = baseServiceUrl;
		this.apiKey = apiKey;
	}

	public String getBaseServiceUrl() {
		return baseServiceUrl;
	}

	public void setBaseServiceUrl(String baseServiceUrl) {
		this.baseServiceUrl = baseServiceUrl;
	}

	public String getApiKey() {
		return apiKey;
	}

	public void setApiKey(String apiKey) {
		this.apiKey = apiKey;
	}

	public String getAuthorization() {
		return String.format(AUTHORIZATION_HEADER, apiKey);
	}

	public String getHeaderAccept() {
		return TYPE_JSON;
	}

	public abstract String getEndpoint();

	public abstract String getMethod();

	public abstract BodyPublisher getBody();

	public abstract String getJson();

	@Override
	public abstract String toString();

	@Override
	public abstract EscRequest clone();

}
