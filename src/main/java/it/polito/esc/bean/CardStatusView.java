package it.polito.esc.bean;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CardStatusView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992051220241219L;

	public static final String DATE_PATTERN = "yyyy-MM-dd";

	public static final String STATUS_ACTIVE = "ACTIVE";
	public static final String STATUS_INACTIVE = "INACTIVE";
	public static final String STATUS_EXPIRED = "EXPIRED";

	/**
	 * The card number using the algorithm RFC-4122 version 1, use /api/v2/cards/generate-escn to generate a valid card number or leave it blank and it will be autogenerated (POST ONLY)
	 * 
	 * example: 3e506370-826a-103d-bf76-001000000001
	 */
	@JsonProperty("cardNumber")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("cardNumber")
	@Expose
	private String cardNumber;

	/**
	 * The name displayed on the card
	 * 
	 * example: Card of John
	 */
	@JsonProperty("displayName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("displayName")
	@Expose
	private String displayName;

	/**
	 * The expiration date of the card (yyyy-MM-dd ISO-8601)
	 * 
	 * example: 2042-12-04
	 */
	@JsonProperty("expiresAt")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("expiresAt")
	@Expose
	private Date expiresAt;

	/**
	 * The issued date of the card (yyyy-MM-dd ISO-8601)
	 * 
	 * example: 1992-12-04
	 */
	@JsonProperty("issuedAt")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("issuedAt")
	@Expose
	private Date issuedAt;

	/**
	 * The identifier of the issuer organisation (PIC)
	 * 
	 * example: 123456789
	 */
	@JsonProperty("issuerIdentifier")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("issuerIdentifier")
	@Expose
	private String issuerIdentifier;

	/**
	 * The name of the issuer organisation
	 * 
	 * example: Université de Saint-Valentin
	 */
	@JsonProperty("issuerName")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("issuerName")
	@Expose
	private String issuerName;

	/**
	 * The status of the card (ACTIVE/INACTIVE/EXPIRED)
	 * 
	 * example: ACTIVE
	 */
	@JsonProperty("status")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("status")
	@Expose
	private String status;

	public CardStatusView() {
		super();
	}

	public CardStatusView(String cardNumber, String displayName, Date expiresAt, Date issuedAt, String issuerIdentifier,
			String issuerName, String status) {
		super();
		this.setCardNumber(cardNumber);
		this.setDisplayName(displayName);
		this.setExpiresAt(expiresAt);
		this.setIssuedAt(issuedAt);
		this.setIssuerIdentifier(issuerIdentifier);
		this.setIssuerName(issuerName);
		this.setStatus(status);
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDisplayName() {
		return displayName;
	}

	public void setDisplayName(String displayName) {
		this.displayName = displayName;
	}

	public Date getExpiresAt() {
		return expiresAt;
	}

	public void setExpiresAt(Date expiresAt) {
		this.expiresAt = expiresAt;
	}

	public Date getIssuedAt() {
		return issuedAt;
	}

	public void setIssuedAt(Date issuedAt) {
		this.issuedAt = issuedAt;
	}

	public String getIssuerIdentifier() {
		return issuerIdentifier;
	}

	public void setIssuerIdentifier(String issuerIdentifier) {
		this.issuerIdentifier = issuerIdentifier;
	}

	public String getIssuerName() {
		return issuerName;
	}

	public void setIssuerName(String issuerName) {
		this.issuerName = issuerName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		if (status != null && !status.equals(STATUS_ACTIVE) && !status.equals(STATUS_INACTIVE)
				&& !status.equals(STATUS_EXPIRED))
			throw new IllegalArgumentException(
					"Invalid status: " + status + " (valid values are: ACTIVE, INACTIVE, EXPIRED)");
		this.status = status;
	}

	@Override
	public String toString() {
		return "CardStatusView [cardNumber=" + cardNumber + ", displayName=" + displayName + ", expiresAt=" + expiresAt
				+ ", issuedAt=" + issuedAt + ", issuerIdentifier=" + issuerIdentifier + ", issuerName=" + issuerName
				+ ", status=" + status + "]";
	}

	@Override
	public CardStatusView clone() {
		return new CardStatusView(this.cardNumber, this.displayName, this.expiresAt, this.issuedAt,
				this.issuerIdentifier, this.issuerName, this.status);
	}

}
