package it.polito.esc.service.request;

import java.net.http.HttpRequest;
import java.net.http.HttpRequest.BodyPublisher;

import it.polito.esc.bean.CardUpdateView;

public class PostCardRequest extends EscRequest {

	private static final long serialVersionUID = 4121992091220241259L;

	/**
	 * The HTTP method of the request
	 */
	private static final String METHOD = "POST";

	/**
	 * The endpoint of the request
	 */
	private static final String ENDPOINT = "/cards";

	private CardUpdateView cardUpdateView;

	public PostCardRequest(String baseServiceUrl, String apiKey, CardUpdateView cardUpdateView) {
		super(baseServiceUrl, apiKey);
		this.setCardUpdateView(cardUpdateView);
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
		return this.baseServiceUrl + ENDPOINT;
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
		return "PostCardRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", baseServiceUrl=" + baseServiceUrl
				+ ", apiKey=" + apiKey + ", cardUpdateView=" + cardUpdateView + "]";
	}

	@Override
	public EscRequest clone() {
		return new PostCardRequest(this.baseServiceUrl, this.apiKey,
				this.cardUpdateView == null ? null : this.cardUpdateView.clone());
	}

}
