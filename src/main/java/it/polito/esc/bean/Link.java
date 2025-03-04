package it.polito.esc.bean;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Link implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992051220241002L;

	@JsonProperty("deprecation")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("deprecation")
	@Expose
	private String deprecation;

	@JsonProperty("href")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("href")
	@Expose
	private String href;

	@JsonProperty("hreflang")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("hreflang")
	@Expose
	private String hreflang;

	@JsonProperty("media")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("media")
	@Expose
	private String media;

	@JsonProperty("name")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("name")
	@Expose
	private String name;

	@JsonProperty("profile")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("profile")
	@Expose
	private String profile;

	@JsonProperty("rel")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("rel")
	@Expose
	private String rel;

	@JsonProperty("title")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("title")
	@Expose
	private String title;

	@JsonProperty("type")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("type")
	@Expose
	private String type;

	public Link() {
		super();
	}

	public Link(String deprecation, String href, String hreflang, String media, String name, String profile, String rel,
			String title, String type) {
		super();
		this.deprecation = deprecation;
		this.href = href;
		this.hreflang = hreflang;
		this.media = media;
		this.name = name;
		this.profile = profile;
		this.rel = rel;
		this.title = title;
		this.type = type;
	}

	public String getDeprecation() {
		return deprecation;
	}

	public void setDeprecation(String deprecation) {
		this.deprecation = deprecation;
	}

	public String getHref() {
		return href;
	}

	public void setHref(String href) {
		this.href = href;
	}

	public String getHreflang() {
		return hreflang;
	}

	public void setHreflang(String hreflang) {
		this.hreflang = hreflang;
	}

	public String getMedia() {
		return media;
	}

	public void setMedia(String media) {
		this.media = media;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getProfile() {
		return profile;
	}

	public void setProfile(String profile) {
		this.profile = profile;
	}

	public String getRel() {
		return rel;
	}

	public void setRel(String rel) {
		this.rel = rel;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public Link clone() {
		return new Link(this.deprecation, this.href, this.hreflang, this.media, this.name, this.profile, this.rel,
				this.title, this.type);
	}

	@Override
	public String toString() {
		return "Link [deprecation=" + deprecation + ", href=" + href + ", hreflang=" + hreflang + ", media=" + media
				+ ", name=" + name + ", profile=" + profile + ", rel=" + rel + ", title=" + title + ", type=" + type
				+ "]";
	}

}
