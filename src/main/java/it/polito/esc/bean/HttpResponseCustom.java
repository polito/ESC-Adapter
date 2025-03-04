package it.polito.esc.bean;

import java.io.Serializable;
import java.net.http.HttpResponse;

public class HttpResponseCustom<T> implements Serializable {
	private static final long serialVersionUID = 231067121831794125L;

	private HttpResponse<?> response;
	private T outputObject;

	public HttpResponseCustom(HttpResponse<String> response, T outputObject) {
		this.response = response;
		this.outputObject = outputObject;
	}

	public HttpResponse<?> getResponse() {
		return response;
	}

	public void setResponse(HttpResponse<?> response) {
		this.response = response;
	}

	public T getOutputObject() {
		return outputObject;
	}

	public void setOutputObject(T outputObject) {
		this.outputObject = outputObject;
	}

}
