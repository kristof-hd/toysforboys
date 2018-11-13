package be.vdab.toysforboysWebApplication.entities;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Version;

@Entity
@Table(name="products")
public class ProductLine implements Serializable {

	private static final long serialVersionUID=1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY) 
	private long id;
	private String name;
	private String description;

	@Version
	private long version;

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getDescription() {
		return description;
	}
	public long getVersion() {
		return version;
	} 
	
	public ProductLine(String name, String description) {
		this.name=name;
		this.description=description;
	}
	protected ProductLine() {
	}
	
}
