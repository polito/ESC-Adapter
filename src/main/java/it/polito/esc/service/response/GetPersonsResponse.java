package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.PagedResourcesPersonLiteView;

public class GetPersonsResponse extends EscResponse<PagedResourcesPersonLiteView> {

	private static final long serialVersionUID = 4121992200620241548L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private PagedResourcesPersonLiteView data;

	@Override
	public PagedResourcesPersonLiteView getData() {
		return data;
	}

	@Override
	public void setData(PagedResourcesPersonLiteView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetPersonResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<PagedResourcesPersonLiteView> clone() {
		GetPersonsResponse clone = new GetPersonsResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
