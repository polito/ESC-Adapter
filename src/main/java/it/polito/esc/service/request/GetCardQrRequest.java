package it.polito.esc.service.request;

import java.net.http.HttpRequest.BodyPublisher;
import java.net.http.HttpRequest.BodyPublishers;

public class GetCardQrRequest extends EscRequest {

	private static final long serialVersionUID = 4121992051220241344L;

	private static final String METHOD = "GET";

	private static final String ENDPOINT = "/cards/%s/qr";

	public static final String MEDIA_TYPE_SVG = "image/svg+xml";
	public static final String MEDIA_TYPE_TEXT = "text/plain";

	public static final String ORIENTATION_VERTICAL = "vertical";
	public static final String ORIENTATION_HORIZONTAL = "horizontal";

	public static final String COLOURS_NORMAL = "normal";
	public static final String COLOURS_INVERTED = "inverted";

	public static final String SIZE_XS = "XS";
	public static final String SIZE_S = "S";
	public static final String SIZE_M = "M";

	private static final String ORIENTATION = "orientation=%s";
	private static final String COLOURS = "colours=%s";
	private static final String SIZE = "size=%s";

	/**
	 * The accepted media type (image/svg+xml or text/plain)
	 * 
	 * Default value : image/svg+xml
	 */
	private String accept;

	/**
	 * Path parameter
	 * 
	 * The number of the card
	 */
	private String escn;

	/**
	 * Query parameter
	 * 
	 * The orientation of the QR [vertical/horizontal]
	 * 
	 * Default value : horizontal
	 */
	private String orientation;

	/**
	 * Query parameter
	 * 
	 * The colours of the QR [normal/inverted]
	 * 
	 * Default value : normal
	 */
	private String colours;

	/**
	 * Query parameter
	 * 
	 * The size of the QR [XS/S/M]
	 * 
	 * Default value : S
	 */
	private String size;

	public GetCardQrRequest(String baseServiceUrl, String apiKey, String escn, String accept) {
		super(baseServiceUrl, apiKey);
		this.setEscn(escn);
		this.setAccept(accept);
		this.setOrientation(null);
		this.setColours(null);
		this.setSize(null);
	}

	public GetCardQrRequest(String baseServiceUrl, String apiKey, String escn, String accept, String orientation,
			String colours, String size) {
		super(baseServiceUrl, apiKey);
		this.setEscn(escn);
		this.setAccept(accept);
		this.setOrientation(orientation);
		this.setColours(colours);
		this.setSize(size);
	}

	public String getAccept() {
		return accept;
	}

	public void setAccept(String accept) {
		if (accept != null && !accept.equals(MEDIA_TYPE_SVG) && !accept.equals(MEDIA_TYPE_TEXT))
			throw new IllegalArgumentException("Invalid accept value");
		this.accept = accept == null ? MEDIA_TYPE_SVG : accept;
	}

	public String getEscn() {
		return escn;
	}

	public void setEscn(String escn) {
		if (escn == null || escn.isEmpty())
			throw new IllegalArgumentException("The id must be not null and not empty");
		this.escn = escn;
	}

	public String getOrientation() {
		return orientation;
	}

	public void setOrientation(String orientation) {
		if (orientation != null && !orientation.equals(ORIENTATION_VERTICAL)
				&& !orientation.equals(ORIENTATION_HORIZONTAL))
			throw new IllegalArgumentException("Invalid orientation value");
		this.orientation = orientation == null ? ORIENTATION_VERTICAL : orientation;
	}

	public String getColours() {
		return colours;
	}

	public void setColours(String colours) {
		if (colours != null && !colours.equals(COLOURS_NORMAL) && !colours.equals(COLOURS_INVERTED))
			throw new IllegalArgumentException("Invalid colours value");
		this.colours = colours == null ? COLOURS_NORMAL : colours;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		if (size != null && !size.equals(SIZE_XS) && !size.equals(SIZE_S) && !size.equals(SIZE_M))
			throw new IllegalArgumentException("Invalid size value");
		this.size = size == null ? SIZE_M : size;
	}

	@Override
	public String getEndpoint() {
		return this.getBaseServiceUrl() + String.format(ENDPOINT, this.getEscn()) + "?"
				+ String.format(ORIENTATION, this.getOrientation()) + "&" + String.format(COLOURS, this.getColours())
				+ "&" + String.format(SIZE, this.getSize());
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
	public String getHeaderAccept() {
		return this.getAccept();
	}

	@Override
	public String toString() {
		return "GetCardQrRequest [accept=" + accept + ", escn=" + escn + ", orientation=" + orientation + ", colours="
				+ colours + ", size=" + size + "]";
	}

	@Override
	public EscRequest clone() {
		return new GetCardQrRequest(this.getBaseServiceUrl(), this.getApiKey(), this.getEscn(), this.getAccept(),
				this.getOrientation(), this.getColours(), this.getSize());
	}

}
