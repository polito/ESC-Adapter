package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PageMetadata implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992051220241014L;

	@JsonProperty("number")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("number")
	@Expose
	private int number;

	@JsonProperty("size")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("size")
	@Expose
	private int size;

	@JsonProperty("totalElements")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("totalElements")
	@Expose
	private int totalElements;

	@JsonProperty("totalPages")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("totalPages")
	@Expose
	private int totalPages;

	public PageMetadata() {
		super();
	}

	public PageMetadata(int number, int size, int totalElements, int totalPages) {
		super();
		this.number = number;
		this.size = size;
		this.totalElements = totalElements;
		this.totalPages = totalPages;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTotalElements() {
		return totalElements;
	}

	public void setTotalElements(int totalElements) {
		this.totalElements = totalElements;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	@Override
	public String toString() {
		return "PageMetadata [number=" + number + ", size=" + size + ", totalElements=" + totalElements
				+ ", totalPages=" + totalPages + "]";
	}

	@Override
	public PageMetadata clone() {
		return new PageMetadata(this.number, this.size, this.totalElements, this.totalPages);
	}

}
