package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CodeView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992041220241359L;

	/**
	 * The key of the entity
	 * 
	 * example: KEY
	 */
	@JsonProperty("key")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("key")
	@Expose
	private String key;

	/**
	 * The label of the entity
	 * 
	 * example: Label
	 */
	@JsonProperty("label")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("label")
	@Expose
	private String label;

	public CodeView() {
		super();
	}

	public CodeView(String key, String label) {
		super();
		this.key = key;
		this.label = label;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	@Override
	public CodeView clone() {
		return new CodeView(this.key, this.label);
	}

	@Override
	public String toString() {
		return "CodeView [key=" + key + ", label=" + label + "]";
	}

}
