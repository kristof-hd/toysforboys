package be.vdab.toysforboysWebApplication.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

import be.vdab.toysforboysWebApplication.entities.Product;

@Embeddable
public class OrderDetail implements Serializable {

	private static final long serialVersionUID=1L; 
	private long quantityOrdered;
	private BigDecimal priceEach; 

	@ManyToOne(optional=false)
	@JoinColumn(name="productId")
	private Product product; 
	
	@Transient
	private boolean deliverable; 
	
	public OrderDetail() {
	}

	public Product getProduct() {
		return product; 
	}
	
	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}
	
	public BigDecimal getValue() {
		return BigDecimal.valueOf(quantityOrdered).multiply(priceEach); 
	}
	
	public boolean isDeliverable() {
		return quantityOrdered<=product.getQuantityInStock(); 
	}

}