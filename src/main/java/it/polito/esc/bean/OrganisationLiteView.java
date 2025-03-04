package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class OrganisationLiteView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992041220241432L;

	/**
	 * Has the organisation accepted the terms and conditions?
	 */
	@JsonProperty("active")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("active")
	@Expose
	private Boolean active;

	@JsonProperty("address")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("address")
	@Expose
	private AddressView address;

	/**
	 * A concatenation of the identifier and the name of the organisation
	 * 
	 * example: 123456789 - Université de Saint-Valentin
	 */
	@JsonProperty("fullLabel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("fullLabel")
	@Expose
	private String fullLabel;

	/**
	 * The identifier of the organisation (PIC/VAT)
	 * example: 123456789
	 */
	@JsonProperty("identifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifier")
	@Expose
	private String identifier;

	@JsonProperty("identifierCode")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("identifierCode")
	@Expose
	private CodeView identifierCode;

	/**
	 * The name of the organisation
	 * 
	 * example: Université de Saint-Valentin
	 */
	@JsonProperty("name")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("name")
	@Expose
	private String name;

	@JsonProperty("organisationType")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("organisationType")
	@Expose
	private CodeView organisationType;

	/**
	 * The SCHAC code of the organisation
	 * 
	 * example: schacHomeOrganization=univ-saint-valentin.fr
	 */
	@JsonProperty("schacHomeOrganization")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("schacHomeOrganization")
	@Expose
	private String schacHomeOrganization;

	/**
	 * The website of the organisation
	 * 
	 * example: www.univ-saint-valentin.fr
	 */
	@JsonProperty("website")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("website")
	@Expose
	private String website;

	public OrganisationLiteView() {
		super();
	}

	public OrganisationLiteView(Boolean active, AddressView address, String fullLabel, String identifier,
			CodeView identifierCode, String name, CodeView organisationType, String schacHomeOrganization,
			String website) {
		super();
		this.active = active;
		this.address = address;
		this.fullLabel = fullLabel;
		this.identifier = identifier;
		this.identifierCode = identifierCode;
		this.name = name;
		this.organisationType = organisationType;
		this.schacHomeOrganization = schacHomeOrganization;
		this.website = website;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public AddressView getAddress() {
		return address;
	}

	public void setAddress(AddressView address) {
		this.address = address;
	}

	public String getFullLabel() {
		return fullLabel;
	}

	public void setFullLabel(String fullLabel) {
		this.fullLabel = fullLabel;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public CodeView getOrganisationType() {
		return organisationType;
	}

	public void setOrganisationType(CodeView organisationType) {
		this.organisationType = organisationType;
	}

	public String getSchacHomeOrganization() {
		return schacHomeOrganization;
	}

	public void setSchacHomeOrganization(String schacHomeOrganization) {
		this.schacHomeOrganization = schacHomeOrganization;
	}

	public String getWebsite() {
		return website;
	}

	public void setWebsite(String website) {
		this.website = website;
	}

	@Override
	public OrganisationLiteView clone() {
		return new OrganisationLiteView(this.active, this.address == null ? null : this.address.clone(), this.fullLabel,
				this.identifier, this.identifierCode == null ? null : this.identifierCode.clone(), this.name,
				this.organisationType == null ? null : this.organisationType.clone(), this.schacHomeOrganization,
				this.website);
	}

	@Override
	public String toString() {
		return "OrganisationLiteView [active=" + active + ", address=" + address + ", fullLabel=" + fullLabel
				+ ", identifier=" + identifier + ", identifierCode=" + identifierCode + ", name=" + name
				+ ", organisationType=" + organisationType + ", schacHomeOrganization=" + schacHomeOrganization
				+ ", website=" + website + "]";
	}

}
