package be.vdab.toysforboysWebApplication.valueobjects;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.NumberFormat;

import be.vdab.toysforboysWebApplication.entities.Product;

@Embeddable
public class OrderDetail implements Serializable {

	private static final long serialVersionUID=1L; 
	private long quantityOrdered;
	@NumberFormat(pattern="0.00") private BigDecimal priceEach; 

	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="productId")
	private Product product; 
	
	public OrderDetail(Product product, long quantityOrdered, BigDecimal priceEach) {
		this.product=product; 
		this.quantityOrdered=quantityOrdered;
		this.priceEach=priceEach; 
	}
	
	protected OrderDetail() {
	}

	public Product getProduct() {
		return product; 
	}
	
	public long getQuantityOrdered() {
		return quantityOrdered;
	}

	public void setQuantityOrdered(long quantityOrdered) {
		this.quantityOrdered = quantityOrdered;
	}

	public BigDecimal getPriceEach() {
		return priceEach;
	}
	
	public BigDecimal getValue() {
		return BigDecimal.valueOf(quantityOrdered).multiply(priceEach); 
	}

	public FormattedNumber getFormattedValue() {
		return new FormattedNumber(BigDecimal.valueOf(quantityOrdered).multiply(priceEach)); 
	}	
	
	public boolean isDeliverable() {
		return quantityOrdered<=product.getQuantityInStock(); 
	}
	
	@Override
	public boolean equals(Object object) {
		if(! (object instanceof OrderDetail)) {
			return false; 
		}
		OrderDetail orderDetail = (OrderDetail) object;
		return product.getName().equalsIgnoreCase(orderDetail.getProduct().getName());
	}
	
	@Override
	public int hashCode() {
		return product.getName().toUpperCase().hashCode(); 
	}

}