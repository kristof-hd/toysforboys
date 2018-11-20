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

import be.vdab.toysforboysWebApplication.valueobjects.Address;

@Entity
@Table(name = "customers")
public class Customer implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	private long id;
	private String name;

	@Embedded
	private Address address;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "countryid")
	private Country country;

	@Version
	private long version;

	public long getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public Address getAddress() {
		return address;
	}

	public Country getCountry() {
		return country;
	}

	public Customer(String name, Address address, Country country) {
		this.name = name;
		this.country=country;
	}

	protected Customer() {
	}

}
