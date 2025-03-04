package it.polito.esc.service.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class EscResponse<T> implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992111220241125L;

	@JsonProperty("code")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected Integer statusCode;

	@JsonProperty("escErrorResponse")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	protected EscErrorResponse escErrorResponse;

	public Integer getStatusCode() {
		return this.statusCode;
	}

	public void setStatusCode(Integer statusCode) {
		this.statusCode = statusCode;
	}

	public EscErrorResponse getEscErrorResponse() {
		return escErrorResponse;
	}

	public void setEscErrorResponse(EscErrorResponse escErrorResponse) {
		this.escErrorResponse = escErrorResponse;
	}

	public abstract T getData();

	public abstract void setData(T data);

	@Override
	public abstract String toString();

	@Override
	public abstract EscResponse<T> clone();

}
