package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonOrganisationView implements Serializable, Cloneable {

	private static final long serialVersionUID = 4121992041220241412L;

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
	private CodeView academicLevel;

	@JsonProperty("contactPoint")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("contactPoint")
	@Expose
	private ContactPointView contactPoint;

	@JsonProperty("organisation")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("organisation")
	@Expose
	private OrganisationLiteView organisation;

	public PersonOrganisationView() {
		super();
	}

	public PersonOrganisationView(CodeView academicLevel, ContactPointView contactPoint,
			OrganisationLiteView organisation) {
		super();
		this.academicLevel = academicLevel;
		this.contactPoint = contactPoint;
		this.organisation = organisation;
	}

	public CodeView getAcademicLevel() {
		return academicLevel;
	}

	public void setAcademicLevel(CodeView academicLevel) {
		this.academicLevel = academicLevel;
	}

	public ContactPointView getContactPoint() {
		return contactPoint;
	}

	public void setContactPoint(ContactPointView contactPoint) {
		this.contactPoint = contactPoint;
	}

	public OrganisationLiteView getOrganisation() {
		return organisation;
	}

	public void setOrganisation(OrganisationLiteView organisation) {
		this.organisation = organisation;
	}

	@Override
	public PersonOrganisationView clone() {
		return new PersonOrganisationView(this.academicLevel == null ? null : this.academicLevel.clone(),
				this.contactPoint == null ? null : this.contactPoint.clone(),
				this.organisation == null ? null : this.organisation.clone());
	}

	@Override
	public String toString() {
		return "PersonOrganisationView [academicLevel=" + academicLevel + ", contactPoint=" + contactPoint
				+ ", organisation=" + organisation + "]";
	}

}
