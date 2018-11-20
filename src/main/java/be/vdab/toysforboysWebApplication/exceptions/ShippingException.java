package be.vdab.toysforboysWebApplication.exceptions;

public class ShippingException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	private long idOfUnshippableOrder; 
	
	public ShippingException() {
	}

	public ShippingException(String description) {
		super(description);
	}
	
	public ShippingException(String description, long idOfUnshippableOrder) {
		super(description);
		this.idOfUnshippableOrder=idOfUnshippableOrder; 
	}

	public long getIdOfUnshippableOrder() {
		return idOfUnshippableOrder; 
	}
	
}
