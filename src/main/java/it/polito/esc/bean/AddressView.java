package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AddressView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992041220241449L;

	/**
	 * The address of the entity
	 * 
	 * example: Appartement 4
	 */
	@JsonProperty("addressName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("addressName")
	@Expose
	private String addressName;

	/**
	 * The city of the entity
	 * 
	 * example: Bruxelles
	 */
	@JsonProperty("cityName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("cityName")
	@Expose
	private String cityName;

	@JsonProperty("country")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("country")
	@Expose
	private CodeView country;

	/**
	 * The street of the entity
	 * 
	 * example: Rue de l'Exemple 12
	 */
	@JsonProperty("streetName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("streetName")
	@Expose
	private String streetName;

	/**
	 * The sub division code of the entity
	 * 
	 * example: BE-BRU
	 */
	@JsonProperty("subdivisionCode")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("subdivisionCode")
	@Expose
	private String subdivisionCode;

	public AddressView() {
		super();
	}

	public AddressView(String addressName, String cityName, CodeView country, String streetName,
			String subdivisionCode) {
		super();
		this.addressName = addressName;
		this.cityName = cityName;
		this.country = country;
		this.streetName = streetName;
		this.subdivisionCode = subdivisionCode;
	}

	public String getAddressName() {
		return addressName;
	}

	public void setAddressName(String addressName) {
		this.addressName = addressName;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public CodeView getCountry() {
		return country;
	}

	public void setCountry(CodeView country) {
		this.country = country;
	}

	public String getStreetName() {
		return streetName;
	}

	public void setStreetName(String streetName) {
		this.streetName = streetName;
	}

	public String getSubdivisionCode() {
		return subdivisionCode;
	}

	public void setSubdivisionCode(String subdivisionCode) {
		this.subdivisionCode = subdivisionCode;
	}

	@Override
	public AddressView clone() {
		return new AddressView(this.getAddressName(), this.getCityName(),
				this.getCountry() == null ? null : this.getCountry().clone(), this.getStreetName(),
				this.getSubdivisionCode());
	}

	@Override
	public String toString() {
		return "AddressView [addressName=" + addressName + ", cityName=" + cityName + ", country=" + country
				+ ", streetName=" + streetName + ", subdivisionCode=" + subdivisionCode + "]";
	}

}
