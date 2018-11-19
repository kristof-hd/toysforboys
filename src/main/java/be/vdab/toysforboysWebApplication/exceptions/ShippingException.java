package be.vdab.toysforboysWebApplication.exceptions;

public class ShippingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ShippingException() {
	}

	public ShippingException(String description) {
		super(description);
	}
}
