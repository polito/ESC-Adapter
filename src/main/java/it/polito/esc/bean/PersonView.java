package it.polito.esc.bean;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonView implements Serializable, Cloneable {

	private static final long serialVersionUID = 4121992041220241357L;

	/**
	 * The full name of the student
	 * example: John Smith
	 * 
	 * Required
	 */
	@JsonProperty("fullName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("fullName")
	@Expose
	private String fullName;

	/**
	 * The european student identifier of the student
	 * example: urn:schac:personalUniqueCode:int:esi:polito.it:s123456
	 * 
	 * Required
	 */
	@JsonProperty("identifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifier")
	@Expose
	private String identifier;

	/**
	 * The european student identifier code of the student
	 */
	@JsonProperty("identifierCode")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifierCode")
	@Expose
	private CodeView identifierCode;

	/*
	 * Number of organisations related to the person
	 */
	@JsonProperty("organisationCount")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("organisationCount")
	@Expose
	private Integer organisationCount;

	/**
	 * uniqueItems: true
	 */
	@JsonProperty("organisations")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("organisations")
	@Expose
	private List<PersonOrganisationView> organisations;

	public PersonView() {
		super();
	}

	public PersonView(String fullName, String identifier, CodeView identifierCode, Integer organisationCount,
			List<PersonOrganisationView> organisations) {
		super();
		this.fullName = fullName;
		this.identifier = identifier;
		this.identifierCode = identifierCode;
		this.organisationCount = organisationCount;
		this.organisations = organisations;
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

	public CodeView getIdentifierCode() {
		return identifierCode;
	}

	public void setIdentifierCode(CodeView identifierCode) {
		this.identifierCode = identifierCode;
	}

	public Integer getOrganisationCount() {
		return organisationCount;
	}

	public void setOrganisationCount(Integer organisationCount) {
		this.organisationCount = organisationCount;
	}

	public List<PersonOrganisationView> getOrganisations() {
		return organisations;
	}

	public void setOrganisations(List<PersonOrganisationView> organisations) {
		this.organisations = organisations;
	}

	@Override
	public PersonView clone() {
		return new PersonView(this.fullName, this.identifier, this.identifierCode.clone(), this.organisationCount,
				this.organisations == null ? null
						: this.organisations.stream().map(PersonOrganisationView::clone).toList());
	}

	@Override
	public String toString() {
		return "PersonView [fullName=" + fullName + ", identifier=" + identifier + ", identifierCode=" + identifierCode
				+ ", organisationCount=" + organisationCount + ", organisations=" + organisations + "]";
	}

}
