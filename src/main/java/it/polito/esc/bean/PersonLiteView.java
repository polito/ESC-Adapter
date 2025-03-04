package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonLiteView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992051220240957L;

	/*
	 * The full name of the student
	 * 
	 * example: John Smith
	 */
	@JsonProperty("fullName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("fullName")
	@Expose
	private String fullName;

	/*
	 * The european student identifier of the student
	 * 
	 * example: urn:schac:personalUniqueCode:int:esi:HR:xxxxxxxxxx
	 */
	@JsonProperty("identifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifier")
	@Expose
	private String identifier;

	public PersonLiteView() {
		super();
	}

	public PersonLiteView(String fullName, String identifier) {
		super();
		this.fullName = fullName;
		this.identifier = identifier;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		this.identifier = identifier;
	}

	@Override
	public String toString() {
		return "PersonLiteView [fullName=" + fullName + ", identifier=" + identifier + "]";
	}

	@Override
	public PersonLiteView clone() {
		return new PersonLiteView(this.fullName, this.identifier);
	}

}
