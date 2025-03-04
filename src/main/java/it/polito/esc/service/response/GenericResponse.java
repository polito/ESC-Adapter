package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenericResponse extends EscResponse<Object> {

	private static final long serialVersionUID = 4121992180620241334L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	Object data;

	public GenericResponse() {
		super();
	}

	@Override
	public void setData(Object data) {
		this.data = data;
	}

	@Override
	public Object getData() {
		return data;
	}

	@Override
	public String toString() {
		return "GenericResponse [code=" + statusCode + ", escErrorResponse=" + escErrorResponse + "]";
	}

	@Override
	public EscResponse<Object> clone() {
		GenericResponse clone = new GenericResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		return clone;
	}

}
