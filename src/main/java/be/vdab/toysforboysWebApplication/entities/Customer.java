package be.vdab.toysforboysWebApplication.entities;

import java.io.Serializable;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.toysforboysWebApplication.valueobjects.Adress;

@Entity
@Table(name="customers")
public class Customer implements Serializable {

	private static final long serialVersionUID=1L; 
	
	@Id
	private long id;
	private String name; 

	@Embedded
	private Adress adress;

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="countryid")
	private Country country; 

	@Version
	private long version;
	
	public long getId() {
		return id; 
	}
	
	public String getName() {
		return name; 
	}
	
	public Adress getAdress() {
		return adress; 
	}
	
	public Country getCountry() {
		return country; 
	}
	
	public void setCountry(Country country) {
		if(country==null) {
			throw new NullPointerException();
		}
		this.country=country; 
	}
	
	public Customer(String name, Adress adress, Country country) {
		this.name=name;
		setCountry(country);
	}
	
	protected Customer() {
	}
	
}
