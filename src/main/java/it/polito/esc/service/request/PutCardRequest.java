package it.polito.esc.service.request;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import it.polito.esc.bean.CardUpdateView;

public class PutCardRequest extends EscRequest {

	private static final long serialVersionUID = 4121992091220241156L;

	/**
	 * The endpoint of the request
	 */
	private static final String ENDPOINT = "/cards/%s";

	/**
	 * The HTTP method of the request
	 */
	private static final String METHOD = "PUT";

	/**
	 * Path variable
	 * 
	 * required: true
	 */
	private String escn;

	private CardUpdateView cardUpdateView;

	public PutCardRequest(String baseServiceUrl, String apiKey, String escn, CardUpdateView cardUpdateView) {
		super(baseServiceUrl, apiKey);
		this.setEscn(escn);
		this.setCardUpdateView(cardUpdateView);
	}

	public String getEscn() {
		return escn;
	}

	public void setEscn(String escn) {
		if (escn == null || escn.isBlank())
			throw new IllegalArgumentException("The european student card number cannot be null or empty");
		this.escn = escn;
	}

	public CardUpdateView getCardUpdateView() {
		return cardUpdateView;
	}

	public void setCardUpdateView(CardUpdateView cardUpdateView) {
		if (cardUpdateView == null)
			throw new IllegalArgumentException("The card update view cannot be null");
		this.cardUpdateView = cardUpdateView;
	}

	@Override
	public String getEndpoint() {
		return baseServiceUrl + String.format(ENDPOINT, this.getEscn());
	}

	@Override
	public String getMethod() {
		return METHOD;
	}

	@Override
	public BodyPublisher getBody() {
		return HttpRequest.BodyPublishers.ofString(getJson());
	}

	@Override
	public String getJson() {
		return GSON.toJson(this.getCardUpdateView());
	}

	@Override
	public String toString() {
		return "PutCardRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", escn=" + escn + ", baseServiceUrl="
				+ baseServiceUrl + ", apiKey=" + apiKey + "]";
	}

	@Override
	public EscRequest clone() {
		return new PutCardRequest(this.baseServiceUrl, this.apiKey, this.escn, this.cardUpdateView.clone());
	}

}
