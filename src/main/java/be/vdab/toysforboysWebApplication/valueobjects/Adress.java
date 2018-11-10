package be.vdab.toysforboysWebApplication.valueobjects;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class Adress implements Serializable {

	private static final long serialVersionUID=1L; 
	
	private String streetAndNumber;
	private String city;
	private String state; 
	private String postalCode;

	public Adress(String streetAndNumber, String city, String state, String postalCode) {
		this.streetAndNumber=streetAndNumber;
		this.city=city;
		this.state=state;
		this.postalCode=postalCode;
	}
	
	protected Adress() {
	}
	
	public String getStreetAndNumber() {
		return streetAndNumber;
	}
	public String getCity() {
		return city;
	}
	public String getState() {
		return state;
	}
	public String getPostalCode() {
		return postalCode;
	}
	
}
