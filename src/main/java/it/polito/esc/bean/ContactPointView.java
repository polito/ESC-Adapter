package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ContactPointView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992041220241414L;

	/**
	 * The email of the contact
	 * 
	 * Required
	 */
	@JsonProperty("email")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("email")
	@Expose
	private String email;

	/**
	 * The fax of the contact
	 */
	@JsonProperty("fax")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("fax")
	@Expose
	private String fax;

	/**
	 * The phone of the contact
	 */
	@JsonProperty("phone")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("phone")
	@Expose
	private String phone;

	public ContactPointView() {
		super();
	}

	public ContactPointView(String email, String fax, String phone) {
		super();
		this.email = email;
		this.fax = fax;
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public ContactPointView clone() {
		return new ContactPointView(email, fax, phone);
	}

	@Override
	public String toString() {
		return "ContactPointView [email=" + email + ", fax=" + fax + ", phone=" + phone + "]";
	}

}
