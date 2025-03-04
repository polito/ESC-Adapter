package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.CardStatusView;

public class GetCardStatusResponse extends EscResponse<CardStatusView> {

	private static final long serialVersionUID = 4121992051220241327L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private CardStatusView data;

	@Override
	public CardStatusView getData() {
		return data;
	}

	@Override
	public void setData(CardStatusView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetCardStatusResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<CardStatusView> clone() {
		GetCardStatusResponse clone = new GetCardStatusResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
