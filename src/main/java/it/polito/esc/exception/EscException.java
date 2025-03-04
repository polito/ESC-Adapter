package it.polito.esc.exception;

public class EscException extends Exception {

	private static final long serialVersionUID = 4121992180620241048L;

	public EscException() {
	}

	public EscException(String message) {
		super(message);
	}

	public EscException(Throwable cause) {
		super(cause);
	}

	public EscException(String message, Throwable cause) {
		super(message, cause);
	}

	public EscException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
		super(message, cause, enableSuppression, writableStackTrace);
	}

}
