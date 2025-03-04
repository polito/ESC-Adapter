package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.CardView;

public class PutCardResponse extends EscResponse<CardView> {

	private static final long serialVersionUID = 4121992091220241219L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private CardView data;

	@Override
	public CardView getData() {
		return data;
	}

	@Override
	public void setData(CardView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PutCardResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<CardView> clone() {
		PutCardResponse clone = new PutCardResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
