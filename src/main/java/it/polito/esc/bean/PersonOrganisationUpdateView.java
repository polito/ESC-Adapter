package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonOrganisationUpdateView implements Serializable, Cloneable {

	private static final long serialVersionUID = 4121992031220241552L;

	/* Possibles values for academic level */

	/**
	 * Bachelor’s degree
	 */
	public static final String ACADEMIC_LEVEL_BACHELOR = "BACHELOR";

	/**
	 * Master’s degree
	 */
	public static final String ACADEMIC_LEVEL_MASTER = "MASTER";

	/**
	 * Doctorate
	 */
	public static final String ACADEMIC_LEVEL_DOCTORATE = "DOCTORATE";

	/**
	 * Current academic level of the student.
	 * Possibles values:
	 * - BACHELOR (Bachelor’s degree)
	 * - MASTER (Master’s degree)
	 * - DOCTORATE (Doctorate)
	 */
	@JsonProperty("academicLevel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("academicLevel")
	@Expose
	private String academicLevel;

	/**
	 * The email of the student
	 * example: student@example.com
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
	 * example: +32 2 555 1234
	 */
	@JsonProperty("fax")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("fax")
	@Expose
	String fax;

	/**
	 * The identifier of the organisation (PIC/VAT)
	 * example: 123456789
	 * 
	 * Required
	 */

	@JsonProperty("organisationIdentifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("organisationIdentifier")
	@Expose
	private String organisationIdentifier;

	/**
	 * The phone of the contact
	 * example: +32 2 555 1234
	 */
	@JsonProperty("phone")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("phone")
	@Expose
	String phone;

	public PersonOrganisationUpdateView() {
		super();
	}

	public PersonOrganisationUpdateView(String email, String organisationIdentifier) {
		super();
		this.setEmail(email);
		this.setOrganisationIdentifier(organisationIdentifier);
	}

	public PersonOrganisationUpdateView(String email, String organisationIdentifier, String academicLevel) {
		super();
		this.setEmail(email);
		this.setOrganisationIdentifier(organisationIdentifier);
		this.setAcademicLevel(academicLevel);
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PersonOrganisationUpdateView(@JsonProperty("email") String email,
			@JsonProperty("organisationIdentifier") String organisationIdentifier,
			@JsonProperty("academicLevel") String academicLevel, @JsonProperty("fax") String fax,
			@JsonProperty("phone") String phone) {
		super();
		this.setEmail(email);
		this.setOrganisationIdentifier(organisationIdentifier);
		this.setAcademicLevel(academicLevel);
		this.setFax(fax);
		this.setPhone(phone);
	}

	public String getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(String academicLevel) {
		if (academicLevel != null && !academicLevel.equals(ACADEMIC_LEVEL_BACHELOR)
				&& !academicLevel.equals(ACADEMIC_LEVEL_MASTER) && !academicLevel.equals(ACADEMIC_LEVEL_DOCTORATE))
			throw new IllegalArgumentException("Invalid academic level value");
		this.academicLevel = academicLevel;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		if (email == null || email.isEmpty() || !email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,}$"))
			throw new IllegalArgumentException("The email is required and cannot be empty and must be a valid email");
		this.email = email;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getOrganisationIdentifier() {
		return organisationIdentifier;
	}

	public void setOrganisationIdentifier(String organisationIdentifier) {
		if (organisationIdentifier == null || organisationIdentifier.isEmpty())
			throw new IllegalArgumentException("The organisation identifier is required and cannot be empty");
		this.organisationIdentifier = organisationIdentifier;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	@Override
	public PersonOrganisationUpdateView clone() {
		return new PersonOrganisationUpdateView(this.getEmail(), this.getOrganisationIdentifier(),
				this.getAcademicLevel(), this.getFax(), this.getPhone());
	}

}
