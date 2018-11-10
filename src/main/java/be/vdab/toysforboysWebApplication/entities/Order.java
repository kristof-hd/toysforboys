package be.vdab.toysforboysWebApplication.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Collections;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedAttributeNode;
import javax.persistence.NamedEntityGraph;
import javax.persistence.Table;

import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail;

@Entity
@Table(name="orders")
@NamedEntityGraph(name=Order.WITH_CUSTOMER, attributeNodes=@NamedAttributeNode("customer"))
public class Order implements Serializable {
	private static final long serialVersionUID=1L;
	public static final String WITH_CUSTOMER="Order.withCustomer"; 
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private long id; 
	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String comments;
	
	@ManyToOne(optional=false, fetch=FetchType.LAZY)
	@JoinColumn(name="customerid")
	private Customer customer; 
	
	@Enumerated(EnumType.STRING)
	private Status status; 
	
	private long version; 
	
	@ElementCollection
	@CollectionTable(name="orderdetails", joinColumns=@JoinColumn(name="orderid"))
	private Set<OrderDetail> orderDetails; 
	
	public long getId() {
		return id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}
	
	public String getComments() {
		return comments;
	}
	
	public void setComments(String comments) {
		this.comments=comments;
	}
	
	public Customer getCustomer() {
		return customer;
	}

	public Status getStatus() {
		return status; 
	}
	
	public void setStatus(Status status) {
		this.status=status; 
	}
	
	public long getVersion() {
		return version;
	}
	
	public Set<OrderDetail> getOrderDetails() {
		return Collections.unmodifiableSet(orderDetails); 
	}
	
	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, Customer customer, Status status) {
		this.orderDate=orderDate;
		this.requiredDate=requiredDate;
		this.shippedDate=shippedDate;
		this.comments=comments; 
		this.customer=customer;
		this.status=status;
		this.orderDetails=new LinkedHashSet<>(); 
	}
	
	protected Order() {
	}
	
	public BigDecimal getTotalValue() {
		return orderDetails.stream().map(orderdetail -> orderdetail.getValue())
				.reduce(BigDecimal.ZERO, (vorigTotaal, huidigeWaarde)->vorigTotaal.add(huidigeWaarde));
	}
}
