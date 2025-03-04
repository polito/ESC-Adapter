package it.polito.esc.bean;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PersonUpdateView implements Serializable {

	private static final long serialVersionUID = 4121992140620241426L;

	/**
	 * The identifier code for the european student identifier
	 * Only ESI is available
	 */
	public static final String IDENTIFIER_CODE = "ESI";

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
	 * The european student identifier code of the student, 'ESI' is the only available option
	 * example: ESI
	 */
	@JsonProperty("identifierCode")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifierCode")
	@Expose
	private String identifierCode;

	/**
	 * uniqueItems: true
	 * 
	 * Required
	 */
	@JsonProperty("personOrganisationUpdateViews")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("personOrganisationUpdateViews")
	@Expose
	private List<PersonOrganisationUpdateView> personOrganisationUpdateViews;

	public PersonUpdateView(String fullName, String identifier,
			List<PersonOrganisationUpdateView> personOrganisationUpdateViews) {
		super();
		this.setFullName(fullName);
		this.setIdentifier(identifier);
		this.setPersonOrganisationUpdateViews(personOrganisationUpdateViews);
	}

	public PersonUpdateView(String fullName, String identifier,
			PersonOrganisationUpdateView personOrganisationUpdateView) {
		super();
		this.setFullName(fullName);
		this.setIdentifier(identifier);
		this.addPersonOrganisationUpdateViews(personOrganisationUpdateView);
	}

	public PersonUpdateView(String fullName, String identifier,
			PersonOrganisationUpdateView personOrganisationUpdateView, String identifierCode) {
		super();
		this.setFullName(fullName);
		this.setIdentifier(identifier);
		this.addPersonOrganisationUpdateViews(personOrganisationUpdateView);
		this.setIdentifierCode(identifierCode);
	}

	@JsonCreator(mode = JsonCreator.Mode.PROPERTIES)
	public PersonUpdateView(@JsonProperty("fullName") String fullName, @JsonProperty("identifier") String identifier,
			@JsonProperty("personOrganisationUpdateViews") List<PersonOrganisationUpdateView> personOrganisationUpdateViews,
			@JsonProperty("identifierCode") String identifierCode) {
		super();
		this.setFullName(fullName);
		this.setIdentifier(identifier);
		this.setPersonOrganisationUpdateViews(personOrganisationUpdateViews);
		this.setIdentifierCode(identifierCode);
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		if (fullName == null || fullName.isEmpty())
			throw new IllegalArgumentException("The full name is required and cannot be empty");
		this.fullName = fullName;
	}

	public String getIdentifier() {
		return identifier;
	}

	public void setIdentifier(String identifier) {
		if (identifier == null || identifier.isEmpty())
			throw new IllegalArgumentException("The identifier is required and cannot be empty");
		this.identifier = identifier;
	}

	public String getIdentifierCode() {
		return identifierCode;
	}

	public void setIdentifierCode(String identifierCode) {
		if (identifierCode != null && !identifierCode.equals(IDENTIFIER_CODE))
			throw new IllegalArgumentException("The identifier code must be " + IDENTIFIER_CODE);
		this.identifierCode = identifierCode;
	}

	public List<PersonOrganisationUpdateView> getPersonOrganisationUpdateViews() {
		return personOrganisationUpdateViews;
	}

	public void setPersonOrganisationUpdateViews(List<PersonOrganisationUpdateView> personOrganisationUpdateViews) {
		if (personOrganisationUpdateViews == null || personOrganisationUpdateViews.isEmpty())
			throw new IllegalArgumentException("The person organisation update views is required and cannot be empty");
		this.personOrganisationUpdateViews = Collections.unmodifiableList(personOrganisationUpdateViews);
	}

	public void addPersonOrganisationUpdateViews(PersonOrganisationUpdateView personOrganisationUpdateView) {
		if (personOrganisationUpdateView == null)
			throw new IllegalArgumentException("The person organisation update view cannot be null");
		if (this.personOrganisationUpdateViews == null)
			this.personOrganisationUpdateViews = Collections
					.unmodifiableList(Arrays.asList(personOrganisationUpdateView));
		else {
			List<PersonOrganisationUpdateView> clonedList = this.personOrganisationUpdateViews.stream()
					.map(PersonOrganisationUpdateView::clone).collect(Collectors.toList());
			clonedList.add(personOrganisationUpdateView.clone());
			this.personOrganisationUpdateViews = Collections.unmodifiableList(clonedList);
		}
	}

	@Override
	public String toString() {
		return "PersonUpdateView [fullName=" + fullName + ", identifier=" + identifier + ", identifierCode="
				+ identifierCode + ", personOrganisationUpdateViews=" + this.getPersonOrganisationUpdateViews() + "]";
	}

	@Override
	public PersonUpdateView clone() {
		return new PersonUpdateView(this.fullName, this.identifier, this.personOrganisationUpdateViews.stream()
				.map(PersonOrganisationUpdateView::clone).collect(Collectors.toList()), this.identifierCode);
	}

}
