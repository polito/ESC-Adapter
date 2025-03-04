package it.polito.esc.service.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import it.polito.esc.bean.PagedResourcesCardLiteView;

public class GetCardsResponse extends EscResponse<PagedResourcesCardLiteView> {

	private static final long serialVersionUID = 4121992091220241410L;

	@JsonProperty("data")
	@SerializedName("data")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@Expose
	private PagedResourcesCardLiteView data;

	@Override
	public PagedResourcesCardLiteView getData() {
		return data;
	}

	@Override
	public void setData(PagedResourcesCardLiteView data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "GetCardsResponse [data=" + data + ", code=" + statusCode + ", escErrorResponse=" + escErrorResponse
				+ "]";
	}

	@Override
	public EscResponse<PagedResourcesCardLiteView> clone() {
		GetCardsResponse clone = new GetCardsResponse();
		clone.setStatusCode(this.statusCode);
		clone.setEscErrorResponse(this.escErrorResponse == null ? null : this.escErrorResponse.clone());
		clone.setData(this.data == null ? null : this.data.clone());
		return clone;
	}

}
