package be.vdab.toysforboysWebApplication.entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Version;

import be.vdab.toysforboysWebApplication.enums.Status;
import be.vdab.toysforboysWebApplication.valueobjects.OrderDetail;

@Entity
@Table(name = "orders")
@NamedEntityGraph(name = Order.WITH_CUSTOMER, attributeNodes = @NamedAttributeNode("customer"))
public class Order implements Serializable {

	private static final long serialVersionUID = 1L;
	public static final String WITH_CUSTOMER = "Order.withCustomer";
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;

	private LocalDate orderDate;
	private LocalDate requiredDate;
	private LocalDate shippedDate;
	private String comments;

	@ManyToOne(optional = false, fetch = FetchType.LAZY)
	@JoinColumn(name = "customerid")
	private Customer customer;

	@Enumerated(EnumType.STRING)
	private Status status;

	@Version
	private long version;

	@ElementCollection
	@CollectionTable(name = "orderdetails", joinColumns = @JoinColumn(name = "orderid"))
	@OrderBy("productid")
	private Set<OrderDetail> orderDetails;

	public long getId() {
		return id;
	}

	public LocalDate getOrderDate() {
		return orderDate;
	}

	public String getFormattedOrderDateWithSlash() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		return orderDate.format(formatter);
	}

	public String getFormattedOrderDateWithHyphen() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yy");
		return orderDate.format(formatter);
	}

	public LocalDate getRequiredDate() {
		return requiredDate;
	}

	public String getFormattedRequiredDateWithSlash() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/MM/yy");
		return requiredDate.format(formatter);
	}

	public String getFormattedRequiredDateWithHyphen() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d-M-yy");
		return requiredDate.format(formatter);
	}

	public LocalDate getShippedDate() {
		return shippedDate;
	}

	public void setShippedDate(LocalDate shippedDate) {
		this.shippedDate = shippedDate;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public Customer getCustomer() {
		return customer;
	}

	public Status getStatus() {
		return status;
	}

	public String getStatusAsString() {
		String statusAsString = status.name().substring(0, 1).toUpperCase() + status.name().substring(1).toLowerCase();
		return statusAsString;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public long getVersion() {
		return version;
	}

	public Set<OrderDetail> getOrderDetails() {
		return Collections.unmodifiableSet(orderDetails);
	}

	public boolean add(OrderDetail orderDetail) {
		return orderDetails.add(orderDetail);
	}

	public boolean remove(OrderDetail orderDetail) {
		return orderDetails.remove(orderDetail);
	}

	public Order(LocalDate orderDate, LocalDate requiredDate, LocalDate shippedDate, String comments, Customer customer,
			Status status) {
		this.orderDate = orderDate;
		this.requiredDate = requiredDate;
		this.shippedDate = shippedDate;
		this.comments = comments;
		this.customer = customer;
		this.status = status;
		this.orderDetails = new LinkedHashSet<>();
	}

	protected Order() {
	}

	public BigDecimal getTotalValue() {
		return orderDetails.stream().map(orderdetail -> orderdetail.getValue()).reduce(BigDecimal.ZERO,
				(vorigTotaal, huidigeWaarde) -> vorigTotaal.add(huidigeWaarde));
	}
}
