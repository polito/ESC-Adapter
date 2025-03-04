package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GetCardQrResponse extends EscResponse<String> {

	private static final long serialVersionUID = 4121992111220241123L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private String data;

	@Override
	public String getData() {
		return data;
	}

	@Override
	public void setData(String data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetCardQrResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<String> clone() {
		GetCardQrResponse clone = new GetCardQrResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data);
		return clone;
	}

}
