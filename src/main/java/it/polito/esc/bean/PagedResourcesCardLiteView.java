package it.polito.esc.bean;

import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class PagedResourcesCardLiteView implements Cloneable, Serializable {

	private static final long serialVersionUID = 4121992051220240942L;

	@JsonProperty("content")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("content")
	@Expose
	private List<CardLiteView> content;

	@JsonProperty("empty")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("empty")
	@Expose
	private boolean empty;

	@JsonProperty("links")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("links")
	@Expose
	private List<Link> links;

	@JsonProperty("page")
	@JsonInclude(JsonInclude.Include.NON_NULL)
	@SerializedName("page")
	@Expose
	private PageMetadata page;

	public PagedResourcesCardLiteView() {
		super();
	}

	public PagedResourcesCardLiteView(List<CardLiteView> content, boolean empty, List<Link> links, PageMetadata page) {
		super();
		this.content = content;
		this.empty = empty;
		this.links = links;
		this.page = page;
	}

	public List<CardLiteView> getContent() {
		return content;
	}

	public void setContent(List<CardLiteView> content) {
		this.content = content;
	}

	public boolean isEmpty() {
		return empty;
	}

	public void setEmpty(boolean empty) {
		this.empty = empty;
	}

	public List<Link> getLinks() {
		return links;
	}

	public void setLinks(List<Link> links) {
		this.links = links;
	}

	public PageMetadata getPage() {
		return page;
	}

	public void setPage(PageMetadata page) {
		this.page = page;
	}

	@Override
	public PagedResourcesCardLiteView clone() {
		return new PagedResourcesCardLiteView(
				this.content == null ? null
						: this.content.stream().map(CardLiteView::clone).collect(Collectors.toList()),
				this.empty,
				this.links == null ? null : this.links.stream().map(Link::clone).collect(Collectors.toList()),
				this.page == null ? null : this.page.clone());
	}

	@Override
	public String toString() {
		return "PagedResourcesCardLiteView [content=" + content + ", empty=" + empty + ", links=" + links + ", page="
				+ page + "]";
	}

}
