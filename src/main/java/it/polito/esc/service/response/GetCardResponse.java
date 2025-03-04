package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.CardView;

public class GetCardResponse extends EscResponse<CardView> {

	private static final long serialVersionUID = 4121992091220241241L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private CardView data;

	@Override
	public CardView getData() {
		return this.data;
	}

	@Override
	public void setData(CardView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetCardResponse [data=" + data + "]";
	}

	@Override
	public EscResponse<CardView> clone() {
		GetCardResponse clone = new GetCardResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
