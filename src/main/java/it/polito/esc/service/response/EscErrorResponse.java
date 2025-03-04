package it.polito.esc.service.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class EscErrorResponse implements Serializable, Cloneable {

	private static final long serialVersionUID = 4121992140620241427L;

	@JsonProperty("code")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("code")
	@Expose
	private String code;

	@JsonProperty("message")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("message")
	@Expose
	private String message;

	public EscErrorResponse() {
		super();
	}

	public EscErrorResponse(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
		return code;
	}
	
	public void setCode(String code) {
		this.code = code;
	}
	
	public String getMessage() {
		return message;
	}
	
	public void setMessage(String message) {
		this.message = message;
	}
	
	@Override
	public EscErrorResponse clone() {
		return new EscErrorResponse(this.code, this.message);
	}
	
	@Override
	public String toString() {
		return "EscErrorResponse [code=" + code + ", message=" + message + "]";
	}

}
