package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;
import java.util.List;

public class GetCardsRequest extends EscRequest {

	private static final long serialVersionUID = 4121992041220241542L;

	private static final String METHOD = "GET";

	private static final String ENDPOINT = "/cards";

	public static final String SORT_CARD_NUMBER = "cardNumber";
	public static final String SORT_ISSUED_AT = "issuedAt";
	public static final String SORT_EXPIRES_AT = "expiresAt";
	public static final String SORT_DISPLAY_NAME = "displayName";
	public static final String SORT_ISSUER = "issuer";
	public static final String SORT_ISSUER_NAME = "issuer.name";
	public static final String SORT_ISSUER_IDENTIFIER = "issuer.identifier";
	public static final String SORT_ISSUER_WEBSITE = "issuer.website";
	public static final String SORT_ISSUER_SCHAC_HOME_ORGANIZATION = "issuer.schacHomeOrganization";
	public static final String SORT_ISSUER_ACTIVE = "issuer.active";
	public static final String SORT_ISSUER_IDENTIFIER_CODE = "issuer.identifierCode";
	public static final String SORT_ISSUER_ORGANISATION_TYPE = "issuer.organisationType";
	public static final String SORT_ISSUER_ADDRESS = "issuer.address";
	public static final String SORT_ISSUER_ADDRESS_STREET_NAME = "issuer.address.streetName";
	public static final String SORT_ISSUER_ADDRESS_ADDRESS_NAME = "issuer.address.addressName";
	public static final String SORT_ISSUER_ADDRESS_CITY_NAME = "issuer.address.cityName";
	public static final String SORT_ISSUER_ADDRESS_SUBDIVISION_CODE = "issuer.address.subdivisionCode";
	public static final String SORT_PERSON = "person";
	public static final String SORT_PERSON_IDENTIFIER = "person.identifier";
	public static final String SORT_PERSON_FULL_NAME = "person.fullName";

	private static final List<String> SORTS = List.of(SORT_CARD_NUMBER, SORT_ISSUED_AT, SORT_EXPIRES_AT,
			SORT_DISPLAY_NAME, SORT_ISSUER, SORT_ISSUER_NAME, SORT_ISSUER_IDENTIFIER, SORT_ISSUER_WEBSITE,
			SORT_ISSUER_SCHAC_HOME_ORGANIZATION, SORT_ISSUER_ACTIVE, SORT_ISSUER_IDENTIFIER_CODE,
			SORT_ISSUER_ORGANISATION_TYPE, SORT_ISSUER_ADDRESS, SORT_ISSUER_ADDRESS_STREET_NAME,
			SORT_ISSUER_ADDRESS_ADDRESS_NAME, SORT_ISSUER_ADDRESS_CITY_NAME, SORT_ISSUER_ADDRESS_SUBDIVISION_CODE,
			SORT_PERSON, SORT_PERSON_IDENTIFIER, SORT_PERSON_FULL_NAME);

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
	private Integer page;

	/**
	 * Query parameter
	 * 
	 * Default value : 10
	 * 
	 * The page size. The value must be greater than 0 and less than or equal to 200
	 */
	private Integer size;

	/**
	 * Query parameter
	 * 
	 * The search string
	 * 
	 */
	private String search;

	public GetCardsRequest(String baseServiceUrl, String apiKey) {
		super(baseServiceUrl, apiKey);
		setPage(DEFAULT_PAGE);
		setSize(DEFAULT_SIZE);
	}

	public GetCardsRequest(String baseServiceUrl, String apiKey, String sort, String direction, Integer page,
			Integer size, String search) {
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
		if (sort != null && !SORTS.contains(sort))
			throw new IllegalArgumentException("The sort value must be one of the following: " + SORTS);
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

	public Integer getSize() {
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
		return "GetCardsRequest [ENDPOINT=" + ENDPOINT + ", METHOD=" + METHOD + ", baseServiceUrl=" + baseServiceUrl
				+ ", apiKey=" + apiKey + ", sort=" + sort + ", direction=" + direction + ", page=" + page + ", size="
				+ size + ", search=" + search + "]";
	}

	@Override
	public EscRequest clone() {
		return new GetCardsRequest(this.baseServiceUrl, this.apiKey, this.sort, this.direction, this.page, this.size,
				this.search);
	}

}
