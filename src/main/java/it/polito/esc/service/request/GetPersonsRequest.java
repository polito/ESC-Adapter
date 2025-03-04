package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class GetPersonsRequest extends EscRequest {

	private static final long serialVersionUID = 4121992041220241542L;

	private static final String METHOD = "GET";

	private static final String ENDPOINT = "/persons";

	public static final String SORT_FULL_NAME = "fullName";
	public static final String SORT_IDENTIFIER = "identifier";

	public static final String SORT_ASC = "ASC";
	public static final String SORT_DESC = "DESC";

	public static final int DEFAULT_PAGE = 0;
	public static final int DEFAULT_SIZE = 10;

	public static final String SORT = "sort=%s";
	public static final String DIRECTION = "direction=%s";
	public static final String PAGE = "page=%d";
	public static final String SIZE = "size=%d";
	public static final String SEARCH = "search=%s";

	/**
	 * Query parameter
	 * 
	 * Possible values: fullName, identifier
	 */
	private String sort;

	/**
	 * Query parameter
	 * 
	 * Default value : ASC
	 * 
	 * Possible values: ASC, DESC
	 */
	private String direction;

	/**
	 * Query parameter
	 * 
	 * Default value : 0
	 * 
	 * The page number. The value must be greater than or equal to 0
	 */
	private Integer page = DEFAULT_PAGE;

	/**
	 * Query parameter
	 * 
	 * Default value : 10
	 * 
	 * The page size. The value must be greater than 0 and less than or equal to 200
	 */
	private Integer size = DEFAULT_SIZE;

	/**
	 * Query parameter
	 * 
	 * The search string
	 * 
	 */
	private String search;

	public GetPersonsRequest(String baseServiceUrl, String apiKey) {
		super(baseServiceUrl, apiKey);
	}

	public GetPersonsRequest(String baseServiceUrl, String apiKey, String sort, String direction, Integer page, Integer size,
			String search) {
		super(baseServiceUrl, apiKey);
		this.setSort(sort);
		this.setDirection(direction);
		this.setPage(page);
		this.setSize(size);
		this.setSearch(search);
	}

	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		if (sort != null && !sort.equals(SORT_FULL_NAME) && !sort.equals(SORT_IDENTIFIER))
			throw new IllegalArgumentException(
					"The sort value must be one of the following: " + SORT_FULL_NAME + ", " + SORT_IDENTIFIER);
		this.sort = sort;
	}

	public String getDirection() {
		return direction;
	}

	public void setDirection(String direction) {
		if (direction != null && !direction.equals(SORT_ASC) && !direction.equals(SORT_DESC))
			throw new IllegalArgumentException(
					"The direction value must be one of the following: " + SORT_ASC + ", " + SORT_DESC);
		this.direction = direction;
	}

	public int getPage() {
		return page;
	}

	public void setPage(Integer page) {
		if (page == null)
			page = DEFAULT_PAGE;
		if (page < 0)
			throw new IllegalArgumentException("The page value must be greater than or equal to 0");
		this.page = page;
	}

	public int getSize() {
		return size;
	}

	public void setSize(Integer size) {
		if (size == null)
            size = DEFAULT_SIZE;
		if (size <= 0 || size > 200)
			throw new IllegalArgumentException("The size value must be greater than 0 and less than or equal to 200");
		this.size = size;
	}

	public String getSearch() {
		return search;
	}

	public void setSearch(String search) {
		this.search = search;
	}

	@Override
	public String getEndpoint() {
		return this.baseServiceUrl + ENDPOINT + "?" + String.format(PAGE, page) + "&" + String.format(SIZE, size)
				+ (sort == null ? "" : "&" + String.format(SORT, sort))
				+ (direction == null ? "" : "&" + String.format(DIRECTION, direction))
				+ (search == null ? "" : "&" + String.format(SEARCH, search));
	}

	@Override
	public String getMethod() {
		return METHOD;
	}

	@Override
	public BodyPublisher getBody() {
		return BodyPublishers.noBody();
	}

	@Override
	public String getJson() {
		return null;
	}

	@Override
	public String toString() {
		return "GetPersonsRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", baseServiceUrl=" + baseServiceUrl
				+ ", apiKey=" + apiKey + ", sort=" + sort + ", direction=" + direction + ", page=" + page + ", size="
				+ size + ", search=" + search + "]";
	}

	@Override
	public EscRequest clone() {
		return new GetPersonsRequest(this.baseServiceUrl, this.apiKey, this.sort, this.direction, this.page, this.size,
				this.search);
	}

}
