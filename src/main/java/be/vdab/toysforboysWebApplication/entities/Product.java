package be.vdab.toysforboysWebApplication.entities;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="products")
public class Product implements Serializable {
	private static final long serialVersionUID=1L; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id;
	private String name;
	private String scale;
	private String description;
	private long quantityInStock;
	private long quantityInOrder; 
	private BigDecimal buyPrice;

	@ManyToOne(fetch=FetchType.LAZY, optional=false) 
	@JoinColumn(name="productlineId")
	public ProductLine productLine;
	
	public ProductLine getProductLine() {
		return productLine; 
	}
	
	private long version;

	public long getId() {
		return id;
	}
	public String getName() {
		return name;
	}
	public String getScale() {
		return scale;
	}
	public String getDescription() {
		return description;
	}
	public long getQuantityInStock() {
		return quantityInStock;
	}
	public long getQuantityInOrder() {
		return quantityInOrder;
	}
	
	public void setQuantityInOrder(long quantityInOrder) {
		this.quantityInOrder=quantityInOrder;
	}

	public void setQuantityInStock(long quantityInStock) {
		this.quantityInStock=quantityInStock;
	}

	
	public BigDecimal getBuyPrice() {
		return buyPrice;
	}
	public long getVersion() {
		return version;
	} 
	
	protected Product() {
	}



}
