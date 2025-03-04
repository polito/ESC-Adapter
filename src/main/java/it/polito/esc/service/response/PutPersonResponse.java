package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.PersonView;

public class PutPersonResponse extends EscResponse<PersonView> {

	private static final long serialVersionUID = 4121992041220241518L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private PersonView data;

	@Override
	public PersonView getData() {
		return data;
	}

	@Override
	public void setData(PersonView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "PutPersonsResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<PersonView> clone() {
		PutPersonResponse clone = new PutPersonResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
